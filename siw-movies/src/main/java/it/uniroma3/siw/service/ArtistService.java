package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired 
	private ArtistRepository artistRepository;
	
	@Transactional
	public void updateArtist(Artist artist) {
		
		artistRepository.save(artist);
	}
	
	@Transactional
	public Artist getArtist(Long id) {
		Optional<Artist> result = this.artistRepository.findById(id);
		return result.orElse(null);
	}

	
	public List<Artist> searchArtistByNameAndSurname(String name, String surname){
		return artistRepository.findByNameAndSurname(name, surname);
	}
	
	public boolean existArtistByNameAndSurname(String name, String surname){
		return artistRepository.existsByNameAndSurname(name, surname);
	}
	
	public List<Artist> searchArtistByName(String name){
		return artistRepository.findByName(name);
	}
	
	@Transactional
    public List<Artist> getAllArtists() {
        List<Artist> result = new ArrayList<>();
        Iterable<Artist> iterable = this.artistRepository.findAll();
        for(Artist artist : iterable)
            result.add(artist);
        return result;
    }
	
	@Transactional
    public List<Artist> getAllByMoviesNotActed(Movie movie) {
        List<Artist> result = new ArrayList<>();
        Iterable<Artist> iterable = this.artistRepository.findAllByMoviesActedInIsNotContaining(movie);
        for(Artist artist : iterable)
            result.add(artist);
        return result;
    }

	@Transactional
    public List<Artist> getAllByMoviesActed(Movie movie) {
        List<Artist> result = new ArrayList<>();
        Iterable<Artist> iterable = this.artistRepository.findAllByMoviesActedInIsContaining(movie);
        for(Artist artist : iterable)
            result.add(artist);
        return result;
    }
	
	
}
