package com.kita.extroverts.repository;


import com.kita.extroverts.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    TODO: Implement custom methods
//    Review findByReviewerName(String name);
//
//    List<Review> findAllByRevieweeId(Long id);
//
//    List<Review> findAllByReviewee(User user);

}
