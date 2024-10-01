package com.yuu.cruddemo.dao;

import com.yuu.cruddemo.entity.Instructor;
import com.yuu.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorByID(int theId);

    void deleteInstructorByID(int theId);

    InstructorDetail findInstructorDetailByID(int theId);

    void deleteInstructorDetailById(int theId);

}
