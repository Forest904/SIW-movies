package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Controller
public class ArtistController {
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping("/operazioniArtists")
    public String operazioniArtists(){
        return "operazioniArtists.html";
    }
	
	@GetMapping(value="/admin/operazioniArtistsAdmin")
    public String operazioniArtistsAdmin(){
        return "admin/operazioniArtistsAdmin.html";
    }
	
	@GetMapping(value="/admin/formNewArtist")
    public String formNewArtist(Model model){
        model.addAttribute("artist", new Artist());
        return "admin/formNewArtist.html";
    }

	
	@PostMapping(value="/admin/artists")
    public String newArtist(@ModelAttribute("artist") Artist artist, Model model) {
        if(!this.artistService.existArtistByNameAndSurname(artist.getName(), artist.getSurname())) {
        	this.artistService.updateArtist(artist);
        	model.addAttribute("artist", artist);
        	return "artist.html";
        }else {
        	model.addAttribute("messaggioErrore", "Questo artista esiste già, inseriscine uno nuovo :)");
            return "admin/formNewArtist.html";
        }
    }
	
	@GetMapping("/artists/{id}")
	public String getArtist(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artist", this.artistService.getArtist(id));
		return "artist.html";
	}
	
	@GetMapping("/artists")
	public String getArtists(Model model) {
		model.addAttribute("artists", this.artistService.getAllArtists());
		return "artists.html";
	}
	
	@GetMapping("/formSearchArtists")
	public String formSearchArtists() {
		return "formSearchArtists.html";
	}

	@PostMapping("/searchArtists")
	public String searchNews(Model model, @RequestParam String name) {
		model.addAttribute("artists", this.artistService.searchArtistByName(name));
		return "foundArtists.html";
	}

	@PostMapping("/admin/saveArtistImage/{id}")
	public String saveArtistImage(@PathVariable("id") Long id,
								  @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
		Artist artist = artistService.getArtist(id);
		if(artist == null) return "errors/artistNotFoundError";

		artist.setPicFilename(fileName);
		artistService.updateArtist(artist);
		String uploadDir = "src/main/upload/images/artist_pics/" + artist.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/admin/artist/"+ id;
	}
	
}
