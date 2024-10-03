package com.yuu.cruddemo.rest;

import com.yuu.cruddemo.entity.Course;
import com.yuu.cruddemo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseRestController {

    private CourseService courseService;

    private CourseRestController(CourseService theCourseService){
        courseService = theCourseService;
    }

    @GetMapping("/courses")
    public List<Course> findAll(){
        return courseService.findAll();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable int courseId){

        Course theCourse = courseService.findById(courseId);
        if(theCourse == null){
            throw new RuntimeException("Course id not found - " + courseId);
        }
        return theCourse;
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course theCourse){
        //also just in case they pass an id in JSON ... set id to 0
        //this is to force a save of new item ... instead of update
        theCourse.setId(0);
        Course dbCourse = courseService.save(theCourse);
        return dbCourse;
    }


    @PutMapping("/courses")
    public Course updateCourse(@RequestBody Course theCourse){
        Course dbCourse = courseService.save(theCourse);
        return dbCourse;
    }

    @DeleteMapping("/courses/{courseId}")
    public String deleteCourse(@PathVariable int courseId){
        Course tempCourse = courseService.findById(courseId);
        //throw exception if null
        if(tempCourse == null){
            throw new RuntimeException("Course id not found - " + courseId);
        }
        courseService.deleteCourseById(courseId);
        return "Deleted course id - " + courseId;
    }
}
