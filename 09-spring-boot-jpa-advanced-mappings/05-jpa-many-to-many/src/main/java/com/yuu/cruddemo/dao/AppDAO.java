package com.yuu.cruddemo.dao;

import com.yuu.cruddemo.entity.Course;
import com.yuu.cruddemo.entity.Instructor;
import com.yuu.cruddemo.entity.InstructorDetail;
import com.yuu.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorByID(int theId);

    void deleteInstructorByID(int theId);

    InstructorDetail findInstructorDetailByID(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);

    void update(Student student);

    void deleteStudentById(int theId);
}
