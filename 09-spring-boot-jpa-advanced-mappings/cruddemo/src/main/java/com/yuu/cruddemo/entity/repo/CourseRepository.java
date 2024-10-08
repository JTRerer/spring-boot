package com.yuu.cruddemo.entity.repo;

import com.yuu.cruddemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
