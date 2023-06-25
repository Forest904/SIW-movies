package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String title;
    @NotNull
    @Max(100)
    @Min(1)
    private Integer score;
    private LocalDateTime creationDateTime;
    private String content;
    @ManyToOne
    private Movie reviewedMovie;
    @ManyToOne
    private User author;

    public Review(){

    }
    public Review(Movie movie, User author){
        this.reviewedMovie = movie;
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(title, review.title) && Objects.equals(creationDateTime, review.creationDateTime) && Objects.equals(author, review.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, creationDateTime, author);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Movie getReviewedMovie() {
        return reviewedMovie;
    }

    public void setReviewedMovie(Movie reviewedMovie) {
        this.reviewedMovie = reviewedMovie;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }
    public String getCreationDateTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" - dd-MM-yyyy - HH:mm");
        return this.creationDateTime.format(formatter);
    }

    public void setCreationDateTime(LocalDateTime date) {
        this.creationDateTime = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}