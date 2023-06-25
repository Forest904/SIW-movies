package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import it.uniroma3.siw.controller.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.MovieRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService {
	
	@Autowired 
	private MovieRepository movieRepository;

	
	@Transactional
	public void updateMovie(Movie movie) {
		movieRepository.save(movie);
	}
	
	@Transactional
	public Movie getMovie(Long id) {
		Optional<Movie> result = this.movieRepository.findById(id);
		return result.orElse(null);
	}

	
	public List<Movie> searchMovieTitleYear(String title, Integer year){
		return movieRepository.findAllByTitleAndYear(title, year);
	}
	
	public List<Movie> searchMovieByYear(Integer year){
		return movieRepository.findByYear(year);
	}
	
	@Transactional
    public List<Movie> getAllMovies() {
        List<Movie> result = new ArrayList<>();
        Iterable<Movie> iterable = this.movieRepository.findAll();
        for(Movie movie : iterable)
            result.add(movie);
        return result;
    }

	public void addImageToMovie(Movie movie, MultipartFile multipartFile) throws IOException {
		//questa linea è necessaria per evitare attacchi di iniezione di codice attraverso il nome del file
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		movie.setImageFileName(fileName);
		String uploadDir = "src/main/upload/images/moviesImages/";
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
	}

}
