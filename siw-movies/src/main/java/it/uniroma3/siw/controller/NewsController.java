package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.uniroma3.siw.model.News;
import it.uniroma3.siw.service.NewsService;



@Controller
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	@GetMapping("/operazioniNews")
    public String operazioniNews(){
        return "operazioniNews.html";
    }
	
	@GetMapping(value="/admin/operazioniNewsAdmin")
    public String operazioniNewsAdmin(){
        return "admin/operazioniNewsAdmin.html";
    }
	
	
	@GetMapping(value="/admin/formNewNews")
	public String formNewNews(Model model) {
		model.addAttribute("news", new News());
		return "admin/formNewNews.html";
	}

	@PostMapping(value="/admin/allNews")
	public String newNews(@ModelAttribute("news") News news, Model model) {
		if (!newsService.existNewsByTitle(news.getTitle())) {
			this.newsService.updateNews(news);
			model.addAttribute("news", news);
			return "news.html";
		} else {
			model.addAttribute("messaggioErrore", "Esiste gia una news con questo titolo, sorry");
			return "admin/formNewNews.html"; 
		}
	}
	
	@GetMapping("/allNews/{id}")
	public String getNews(@PathVariable("id") Long id, Model model) {
		model.addAttribute("news", this.newsService.getNews(id));
		return "news.html";
	}

	@GetMapping("/allNews")
	public String showAllNews(Model model) {
		model.addAttribute("allNews", this.newsService.getAllNews());
		return "allNews.html";
	}
	
	@GetMapping("/formSearchAllNews")
	public String formSearchAllNews() {
		return "formSearchAllNews.html";
	}

	@PostMapping("/searchNews")
	public String searchNews(Model model, @RequestParam String title) {
		model.addAttribute("allNews", this.newsService.searchNewsByTitle(title));
		return "foundAllNews.html";
	}
	
}
