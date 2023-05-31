package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.News;

public interface NewsRepository extends CrudRepository<News, Long>{
	
	public boolean existsByTitle(String title);
	
	public Optional<News> findById(Long id);
	
	public List<News> findByTitle(String title);

}
