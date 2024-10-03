package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.Course;
import com.yuu.cruddemo.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository theCourseRepository){
        courseRepository = theCourseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int theId) {
        Optional<Course> result = courseRepository.findById(theId);
        Course theCourse = null;
        if (result.isPresent()){
            theCourse = result.get();
        }
        else{
            throw new RuntimeException("Course id not found - " + theId);
        }
        return theCourse;
    }

    @Override
    public Course save(Course theCourse) {
        return courseRepository.save(theCourse);
    }

    @Override
    public void deleteCourseById(int theId) {
        courseRepository.deleteById(theId);
    }
}
