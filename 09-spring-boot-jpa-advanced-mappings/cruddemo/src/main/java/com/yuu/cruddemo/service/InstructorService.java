package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InstructorService {

    List<Instructor> findAll();

    Instructor findById(int theId);

    Instructor save(Instructor theInstructor);

    void deleteInstructorById(int theId);
}
