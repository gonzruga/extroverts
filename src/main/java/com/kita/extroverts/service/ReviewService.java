package com.kita.extroverts.service;

import com.kita.extroverts.dto.ReviewDto;
import com.kita.extroverts.model.Review;
import com.kita.extroverts.model.User;
import com.kita.extroverts.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final UserService userService; //Added in order to connect review to a User.

// CREATE - POST
    public Review saveReview(ReviewDto review) {
        return repository.save(review.dtoToReview());
    }

// READ - GET
    public Review getReviewById(Long id) {
    return repository.findById(id).orElse(null);
}

    public List<Review> getReviews() {
        return repository.findAll();
    }

    //TODO: test custom methods
//    public Review getReviewByReviewerName(String name) { return repository.findByReviewerName(name); }
//
//    public List<Review> getReviewsByRevieweeId(Long id) {
//        return repository.findAllByRevieweeId(id);
//    }
//
//    public List<Review> getReviewsByReviewee(User user) {
//        return repository.findAllByReviewee(user);
//    }


// DELETE
    public String deleteReview(long id){
//        repository.deleteAllById(Collections.singleton(id));
        repository.deleteById(id);
        return "Removed Review ID: " + id;
    }

// UPDATE - PUT
    public Review updateReview(ReviewDto review){
        Review existingReview = repository.findById(review.getId()).orElse(null);

        existingReview.setContent(review.getContent());
        existingReview.setName(review.getName());
        // Reviewer should not be changed
        // TODO Replace Writer Reviewer name automatically with user name when security is fully implemented

        existingReview.setUpdatedAt(new Date());

        return repository.save(existingReview);

    }

}
