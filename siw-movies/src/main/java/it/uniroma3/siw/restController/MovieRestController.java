package it.uniroma3.siw.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.MovieService;

@RestController
public class MovieRestController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping(value = "/rest/movie/{id}")
	public Movie getMovie(@PathVariable("id") Long id) {
		return this.movieService.getMovie(id);
	}

	@GetMapping(value = "/rest/movies")
	public List<Movie> getMovies() {
		return this.movieService.getAllMovies();
	}
	
	@GetMapping(value="/rest/searchMoviesByYear")
	public List<Movie> getMoviesByYear(Integer year){
		return this.movieService.searchMovieByYear(year);
	}

}
