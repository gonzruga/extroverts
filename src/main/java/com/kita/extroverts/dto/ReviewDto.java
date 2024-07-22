package com.kita.extroverts.dto;

import com.kita.extroverts.model.Review;
import com.kita.extroverts.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;

    private String content;
    private String name;

    private UUID revieweeId;   //Only here, used in reviewSubmit
    private User reviewee; //Used in ReviewController: 'reviewSubmit'

    private Date createdAt = new Date();
    private Date updatedAt;

    public Review dtoToReview() {
        final Review review = new Review();

        review.setId(id);
        review.setContent(content);
        review.setName(name);

        review.setReviewee(reviewee);

        return review;
    }
}
