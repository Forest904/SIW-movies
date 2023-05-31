package it.uniroma3.siw.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;

@RestController
public class ArtistRestController {
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping(value = "/rest/artist/{id}")
	public Artist getArtist(@PathVariable("id") Long id) {
		return this.artistService.getArtist(id);
	}

	@GetMapping(value = "/rest/artists")
	public List<Artist> getArtists() {
		return this.artistService.getAllArtists();
	}
	
	@GetMapping(value="/rest/searchArtistByName")
	public List<Artist> getArtistsByName(String name){
		return this.artistService.searchArtistByName(name);
	}

}
