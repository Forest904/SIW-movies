package it.uniroma3.siw.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ReviewRepository;
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public void save(Review review){
        reviewRepository.save(review);
    }

    @Transactional
    public void remove(Review review){
        reviewRepository.delete(review);
    }

    public Review findById(Long id){
        return reviewRepository.findById(id).orElse(null);
    }
}
