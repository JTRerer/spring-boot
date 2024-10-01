package com.yuu.cruddemo.dao;

import com.yuu.cruddemo.entity.Instructor;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorByID(int theId);

    void deleteInstructorByID(int theId);
}
