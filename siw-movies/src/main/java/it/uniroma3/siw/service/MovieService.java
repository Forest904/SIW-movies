package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.MovieRepository;

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

}
