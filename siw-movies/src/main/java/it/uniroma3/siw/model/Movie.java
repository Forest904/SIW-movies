package it.uniroma3.siw.model;


import java.util.*;
import javax.persistence.*;
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

	private String imageFileName;
    
    @NotNull
    @Min(1900)
    @Max(2023)
	private Integer year;

	@Column(nullable = true)
	private String picFilename;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	private Artist director;

	
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Artist> actors;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "reviewedMovie")
	private Set<Review> reviews;

    public Movie() {
        this.actors = new ArrayList<Artist>();
		this.reviews = new HashSet<>();
    }

	public String getPicPath(){
		if(picFilename != null) return "/upload/images/movie_pics/" + this.getId() + "/"
				+this.getPicFilename();
		return "/images/default_movie_pic.png";
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

	public String getAvgReviews(){
		int sum=0;
		if (reviews.size()==0) return "00";
		for (Review r:
				reviews) {
			sum += r.getScore();
		}
		int res = sum/reviews.size();

		if(res<10) return "0"+res;

		return "" + res;
	}
	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getPicFilename() {
		return picFilename;
	}

	public void setPicFilename(String picFilename) {
		this.picFilename = picFilename;
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
