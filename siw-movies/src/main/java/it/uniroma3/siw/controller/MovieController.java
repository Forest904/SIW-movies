package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Objects;
import javax.validation.Valid;

import it.uniroma3.siw.controller.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.validator.MovieValidator;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
    MovieValidator movieValidator;
	
	
	@GetMapping("/index")
	public String index() {
		return "index.html";
	}
		
	
	@GetMapping("/operazioniMovies")
    public String operazioniMovies(){
        return "operazioniMovies.html";
    }

    
    
    @GetMapping(value="/admin/operazioniMoviesAdmin")
    public String operazioniMoviesAdmin(){
        return "admin/operazioniMoviesAdmin.html";
    }

    
	
    @GetMapping(value="/admin/formNewMovie")
    public String formNewMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "admin/formNewMovie.html";
    }
    
    @PostMapping(value="/admin/movies")
    public String newMovie(@RequestParam("image") MultipartFile multipartFile, @Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, Model model) {
        this.movieValidator.validate(movie, bindingResult);
        if (!bindingResult.hasErrors() && !multipartFile.isEmpty()) {
            try{
                movieService.addImageToMovie(movie, multipartFile);
            } catch (IOException e) {
                model.addAttribute("erroreUpload", "Errore nel caricamento dell'immagine");
                return returnToFormNewMovie(model);
            }

            if(movie.getDirector() != null){
                movie.getDirector().getDirectedMovies().add(movie);
            }

            this.movieService.updateMovie(movie);
            model.addAttribute("movie", movie);
            return "movie.html";
        } else {
            model.addAttribute("messaggioErrore", "Questo film esiste già, inseriscine uno nuovo :)");
            return returnToFormNewMovie(model);
        }
    }


    //we need this to return to the form with the error messages, and eliminate duplicate code
    public String returnToFormNewMovie(Model model){
        if(artistService.getAllArtists().size() != 0)
            model.addAttribute("directors", artistService.getAllArtists());
        return "admin/formNewMovie.html";
    }
    
    @GetMapping("/movies")
	public String showMovies(Model model) {
		model.addAttribute("movies", this.movieService.getAllMovies());
		return "movies.html";
	}
    

	@GetMapping("/movies/{id}")
    public String getMovie(@PathVariable("id") Long id, Model model) {
        model.addAttribute("movie", this.movieService.getMovie(id));
        model.addAttribute("cast", this.artistService.getAllByMoviesActed(this.movieService.getMovie(id)));
        return "movie.html";
    }

	@GetMapping("/formSearchMovies")
	public String formSearchMovies() {
		return "formSearchMovies.html";
	}

	@PostMapping("/searchMovies")
	public String searchMovies(Model model, @RequestParam Integer year) {
		model.addAttribute("movies", this.movieService.searchMovieByYear(year));
		return "foundMovies.html";
	}
	
	@GetMapping(value= "/admin/gestisciMovies")
	public String gestisciMovies(Model model) {
		model.addAttribute("movies", this.movieService.getAllMovies());
		return "admin/gestisciMovies.html";
	}
	
	
	@GetMapping(value="/admin/formUpdateMovies/{id}")
    public String formUpdateMovies(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.getMovie(id);
        model.addAttribute("movie", movie);
        model.addAttribute("director", movie.getDirector());
        model.addAttribute("directors", artistService.getAllArtists());
        return "admin/formUpdateMovies.html";
    }
	
	
    @GetMapping(value= "/admin/directorsToAdd/{movieId}")
    public String showDirectorsList(@PathVariable("movieId") Long movieId, Model model){
        model.addAttribute("movie", movieService.getMovie(movieId));
        model.addAttribute("directors", artistService.getAllArtists());
        return "admin/directorsToAdd.html";
    }
    
    
    

    @GetMapping(value="/admin/allActorsForMovie/{movieId}")
    public String showActorListForMovie(@PathVariable("movieId") Long movieId, Model model) {
        Movie movie = movieService.getMovie(movieId);
        List<Artist> cast = artistService.getAllByMoviesActed(movie);
        model.addAttribute("movie", movie);
        
        if(movie.getActors() == null) {
            movie.setActors(new LinkedList<Artist>(cast));
            movieService.updateMovie(movie);
        }

        model.addAttribute("notInMovieActors", artistService.getAllByMoviesNotActed(movie));
        model.addAttribute("inMovieActors", cast);
        return "admin/allActorsForMovie.html";
    }

    @GetMapping(value="/admin/removeActorFromMovie/{movieId}/{actorId}")
    public String removeActorFromMovie( @PathVariable("movieId") Long movieId, @PathVariable("actorId") Long actorId, Model model) {
        Movie movie = movieService.getMovie(movieId);
        List<Artist> notCast = artistService.getAllByMoviesNotActed(movie);
        List<Artist> cast = artistService.getAllByMoviesActed(movie);

        Artist actorToRemove = artistService.getArtist(actorId);
        cast.remove(actorToRemove);
        notCast.add(actorToRemove);
        movie.getActors().remove(actorToRemove);
        actorToRemove.getMoviesActedIn().remove(movie);

        movieService.updateMovie(movie);

        model.addAttribute("movie", movie);
        model.addAttribute("notInMovieActors", notCast);
        model.addAttribute("inMovieActors", cast);
        return "admin/allActorsForMovie.html";
    }

    @GetMapping(value="/admin/addActorInMovie/{movieId}/{actorId}")
    public String addActorInMovie( @PathVariable("movieId") Long movieId, @PathVariable("actorId") Long actorId, Model model) {
        Movie movie = movieService.getMovie(movieId);
        List<Artist> cast = artistService.getAllByMoviesActed(movie);
        List<Artist> notCast = artistService.getAllByMoviesNotActed(movie);
        Artist actorToAdd = artistService.getArtist(actorId);
        cast.add(actorToAdd);
        notCast.remove(actorToAdd);
        movie.getActors().add(actorToAdd);
        actorToAdd.getMoviesActedIn().add(movie);

        movieService.updateMovie(movie);

        model.addAttribute("movie", movie);
        model.addAttribute("notInMovieActors", notCast);
        model.addAttribute("inMovieActors", cast);
        return "admin/allActorsForMovie.html";
    }
    
    @GetMapping(value= "/admin/addDirectorToMovie/{movieId}/{dirId}")
    public String addDirectorToMovie(@PathVariable("movieId") Long movieId, @PathVariable("dirId") Long directorId, Model model){
        Movie movie = movieService.getMovie(movieId);
        Artist director = artistService.getArtist(directorId);
        if(movie.getDirector()!= null){ movie.getDirector().getDirectedMovies().remove(movie);}
        
        movie.setDirector(director);
        movieService.updateMovie(movie);
        model.addAttribute("movie", movie);
        return "admin/formUpdateMovies.html";
    }

    @PostMapping("/saveMovieImage/{id}")
    public String saveMovieImage(@PathVariable("id") Long id,
                                 @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Movie movie = movieService.getMovie(id);
        if(movie == null) return "errors/movieNotFoundError";

        movie.setPicFilename(fileName);
        movieService.updateMovie(movie);
        String uploadDir = "src/main/upload/images/movie_pics/" + movie.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/movie/"+ id;
    }

}
