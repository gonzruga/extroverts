package com.kita.extroverts.controller;

import com.kita.extroverts.dto.ReviewDto;
import com.kita.extroverts.model.Review;
import com.kita.extroverts.service.ReviewService;
import com.kita.extroverts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


//@RestController  // Data
//@AllArgsConstructor
@Slf4j
@Controller // For attaching FORM since 'RestController' does not.
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Autowired
    private final UserService userService;

// CREATE - POST

    @GetMapping("/reviewForm/{revieweeId}/{revieweeName}")
    public String reviewForm(
            @PathVariable(name="revieweeId") UUID revieweeId, @PathVariable(name="revieweeName") String revieweeName,
            Model model
    ) {
        Review review = new Review();
        model.addAttribute("review", review);  // Name & value of attribute.
        model.addAttribute("revieweeId", revieweeId);
        model.addAttribute("revieweeName", revieweeName);
        return "review-form";
    }

    @PostMapping("/reviewSubmit")
    public String reviewSubmit(@ModelAttribute ReviewDto review, Model model, @RequestParam("revieweeId") UUID revieweeId) {
        model.addAttribute("review", review);  // Name & value of attribute.
//        log.info("{}",revieweeId);  // .info error
        UUID id = revieweeId;
        model.addAttribute("id", id);
//        model.addAttribute("revieweeId", revieweeId);
//        review.setReviewee(userService.getUserById(revieweeId));
        model.addAttribute("id", id);
        review.setReviewee(userService.getUserById(id));
        review.setRevieweeId(id);
        service.saveReview(review);

        return "redirect:/userPage/{id}";
    }
// ptional long parameter 'revieweeId' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.] with root cause

// READ - GET
    @GetMapping("/reviewPage/{id}")
    public String reviewPage(@PathVariable long id, Model model){
        Review review = service.getReviewById(id);
        model.addAttribute("review", review);
        return "review-page";
    }

    @GetMapping("/reviewList")
    public String findAllReviews(Model model) {
        model.addAttribute("reviews", service.getReviews());
        return "review-list";
    }
// UPDATE - PUT
    @GetMapping("/reviewEdit/{id}")
    public String editReview(@PathVariable long id, Model model){
    Review review = new Review();
    review = service.getReviewById(id);
    model.addAttribute("review", review);
    return "review-edit";
    }

    @PostMapping("/reviewUpdate/{id}")
    public String updateReview(@ModelAttribute ReviewDto review, @PathVariable long id) {
        service.updateReview(review);
        return "redirect:/reviewPage/{id}";
    }

// DELETE
    @GetMapping("/reviewDelete/{reviewId}/{revieweeId}")
    public String deleteReview(@PathVariable long reviewId, long revieweeId){
        service.deleteReview(reviewId);
    return "redirect:/userPage/{revieweeId}";
    }

}