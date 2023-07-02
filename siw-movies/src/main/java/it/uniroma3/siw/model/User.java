package it.uniroma3.siw.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users") // cambiamo nome perchè in postgres user e' una parola riservata
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String name;
	private String surname;
	private String email;

	@Column(nullable = true, length = 64)
	private String picFilename;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Movie> watchList;
	@OneToMany(mappedBy = "author")
	private Set<Review> reviews;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicPath(){
		if(picFilename != null) return "/upload/images/user_pics/" + this.getId() + "/"
				+this.getPicFilename();
		return "/images/default_profile_pic.png";
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Movie> getWatchList() {
		return watchList;
	}

	public void setWatchList(Set<Movie> watchList) {
		this.watchList = watchList;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public String getPicFilename() {
		return picFilename;
	}

	public void setPicFilename(String picFilename) {
		this.picFilename = picFilename;
	}
}