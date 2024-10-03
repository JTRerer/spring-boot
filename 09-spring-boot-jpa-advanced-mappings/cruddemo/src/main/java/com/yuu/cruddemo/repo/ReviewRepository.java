package com.yuu.cruddemo.repo;

import com.yuu.cruddemo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
