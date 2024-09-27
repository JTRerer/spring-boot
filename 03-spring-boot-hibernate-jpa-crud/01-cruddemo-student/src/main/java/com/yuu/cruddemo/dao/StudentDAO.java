package com.yuu.cruddemo.dao;

import com.yuu.cruddemo.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

}
