package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String title;
	private String text;
	private LocalDateTime publishing_date;
	
	
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getPublishing_date() {
		return publishing_date;
	}
	public void setDate(LocalDateTime publishing_date) {
		this.publishing_date = publishing_date;
	}
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        News news = (News) o;
	        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(text, news.text) && Objects.equals(publishing_date, news.publishing_date);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, title, text, publishing_date);
	    }

}
