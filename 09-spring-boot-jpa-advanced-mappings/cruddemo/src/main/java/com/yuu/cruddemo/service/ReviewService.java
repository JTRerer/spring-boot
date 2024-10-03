package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> findAll();

    Review findById(int theId);

    Review save(Review theReview);

    void deleteReviewById(int theId);
}
