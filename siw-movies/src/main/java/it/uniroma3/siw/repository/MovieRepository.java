package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Movie;

import javax.persistence.criteria.CriteriaBuilder;

public interface MovieRepository extends CrudRepository<Movie, Long> {

	public List<Movie> findByYear(Integer year);

	public boolean existsByTitleAndYear(String title, Integer year);
	
	public List<Movie> findAllByOrderByYearDesc();
	
	public List<Movie> findAllByTitleAndYear(String title, Integer year);
}