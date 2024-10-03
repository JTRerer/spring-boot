package com.yuu.cruddemo.rest;

import com.yuu.cruddemo.entity.Review;
import com.yuu.cruddemo.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewRestController {

    private ReviewService reviewService;

    private ReviewRestController(ReviewService theReviewService){
        reviewService = theReviewService;
    }

    @GetMapping("/reviews")
    public List<Review> findAll(){
        return reviewService.findAll();
    }

    @GetMapping("/reviews/{reviewId}")
    public Review getReview(@PathVariable int reviewId){

        Review theReview = reviewService.findById(reviewId);
        if(theReview == null){
            throw new RuntimeException("Review id not found - " + reviewId);
        }
        return theReview;
    }

    @PostMapping("/reviews")
    public Review addReview(@RequestBody Review theReview){
        //also just in case they pass an id in JSON ... set id to 0
        //this is to force a save of new item ... instead of update
        theReview.setId(0);
        Review dbReview = reviewService.save(theReview);
        return dbReview;
    }


    @PutMapping("/reviews")
    public Review updateReview(@RequestBody Review theReview){
        Review dbReview = reviewService.save(theReview);
        return dbReview;
    }

    @DeleteMapping("/reviews/{reviewId}")
    public String deleteReview(@PathVariable int reviewId){
        Review tempReview = reviewService.findById(reviewId);
        //throw exception if null
        if(tempReview == null){
            throw new RuntimeException("Review id not found - " + reviewId);
        }
        reviewService.deleteReviewById(reviewId);
        return "Review course id - " + reviewId;
    }
}
