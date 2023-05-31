package it.uniroma3.siw.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
	private String title;
    
    @NotNull
    @Min(1900)
    @Max(2023)
	private Integer year;
	private String urlImage;
	
	@OneToMany
	private List<News> movieNews;

	
	
	@ManyToOne(cascade = {CascadeType.ALL})
	private Artist director;
	
	@OneToMany
    @JoinColumn(name = "movie_id")
    private List<News> news;
	
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Artist> actors;

    public Movie() {
        this.news = new ArrayList<News>();
        this.actors = new ArrayList<Artist>();
    }
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	public Artist getDirector() {
        return director;
    }

    public void setDirector(Artist director) {
        this.director = director;
        director.getDirectedMovies().add(this);
    }

    public List<Artist> getActors() {return actors;}

    public void setActors(List<Artist> actors) {
        this.actors = actors;
    }

    public void addActor(Artist actor) {
        this.actors.add(actor);
        actor.getMoviesActedIn().add(this);
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(title, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(title, other.title) && year == other.year;
	}
}
