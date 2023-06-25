package it.uniroma3.siw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.service.ReviewService;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.service.MovieService;
@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/registered/formNewReview/{movie_id}")
    public String formNewReview(@PathVariable("movie_id") Long movie_id,Model model){
        model.addAttribute("review", new Review());
        model.addAttribute("movie_id", movie_id);
        return "registered/formNewReview";
    }
    @PostMapping("/registered/review/{movie_id}")
    public String newMovie(@PathVariable("movie_id") Long movie_id,@Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model){
        if(!userService.canReview(userService.getCurrentUser(), movieService.getMovie(movie_id))){
            return "errors/cannotCreateMoreReview";
        }
        if(!bindingResult.hasErrors()){
            Movie movie = movieService.getMovie(movie_id);
            if(movie == null) return "errors/movieNotFoundError";

            review.setReviewedMovie(movie);
            review.setAuthor(userService.getCurrentUser());
            review.setCreationDateTime(LocalDateTime.now());
            this.reviewService.save(review);
            model.addAttribute("review", review);
            return "redirect:/movie/" + movie_id;
        }else{
            return "registered/formNewReview";
        }
    }
    @GetMapping("/registered/removeOwnReview/{review_id}")
    public String removeOwnReview(@PathVariable("review_id") Long id, Model model){
        Review review = reviewService.findById(id);
        if(review == null ||
                !userService.getCurrentUser().equals(review.getAuthor()))
            return "errors/reviewNotFoundError";
        Movie movie = review.getReviewedMovie();
        reviewService.remove(review);
        return "redirect:/movie/"+movie.getId();
    }
    //########### ADMIN #################
    @GetMapping("/admin/removeReview/{review_id}")
    public String removeReview(@PathVariable("review_id") Long id, Model model){
        Review review = reviewService.findById(id);
        if(review == null) return "errors/reviewNotFoundError";

        Movie movie = review.getReviewedMovie();
        reviewService.remove(review);
        return "redirect:/admin/movie/"+movie.getId();
    }
}