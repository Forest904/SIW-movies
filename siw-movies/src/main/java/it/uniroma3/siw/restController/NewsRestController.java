package it.uniroma3.siw.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import it.uniroma3.siw.model.News;
import it.uniroma3.siw.service.NewsService;

@RestController
public class NewsRestController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping(value = "/rest/news/{id}")
	public News getNews(@PathVariable("id") Long id) {
		return this.newsService.getNews(id);
	}

	@GetMapping(value = "/rest/allNews")
	public List<News> getAllNews() {
		return this.newsService.getAllNews();
	}
	
	@GetMapping(value="/rest/searchNewsByTitle")
	public List<News> getNewsByTitle(String title){
		return this.newsService.searchNewsByTitle(title);
	}

}
