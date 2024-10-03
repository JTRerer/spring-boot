package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.Course;
import com.yuu.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface CourseService {

    List<Course> findAll();

    Course findById(int theId);

    Course save(Course theCourse);

    void deleteCourseById(int theId);
}
