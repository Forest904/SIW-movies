package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.News;
import it.uniroma3.siw.repository.NewsRepository;

@Service
public class NewsService {
	
	@Autowired 
	private NewsRepository newsRepository;
	
	@Transactional
	public void updateNews(News news) {
		newsRepository.save(news);
	}
	
	@Transactional
	public News getNews(Long id) {
		Optional<News> result = this.newsRepository.findById(id);
		return result.orElse(null);
	}

	public boolean existNewsByTitle(String title){
		return newsRepository.existsByTitle(title);
	}
	
	public List<News> searchNewsByTitle(String title){
		return newsRepository.findByTitle(title);
	}

	@Transactional
    public List<News> getAllNews() {
        List<News> result = new ArrayList<>();
        Iterable<News> iterable = this.newsRepository.findAll();
        for(News news : iterable)
            result.add(news);
        return result;
    }
}
