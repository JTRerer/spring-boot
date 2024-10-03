package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.Review;
import com.yuu.cruddemo.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository theReviewRepository){
        reviewRepository = theReviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(int theId) {
        Optional<Review> result = reviewRepository.findById(theId);
        Review theReview = null;
        if (result.isPresent()){
            theReview = result.get();
        }
        else{
            throw new RuntimeException("Review id not found - " + theId);
        }
        return theReview;
    }

    @Override
    public Review save(Review theReview) {
        return reviewRepository.save(theReview);
    }

    @Override
    public void deleteReviewById(int theId) {
        reviewRepository.deleteById(theId);
    }
}
