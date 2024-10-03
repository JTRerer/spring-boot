package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(int theId);

    Student save(Student theStudent);

    void deleteStudentById(int theId);
}

