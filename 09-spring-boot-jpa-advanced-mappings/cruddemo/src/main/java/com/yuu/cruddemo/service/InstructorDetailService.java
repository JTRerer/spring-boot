package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface InstructorDetailService {

    List<InstructorDetail> findAll();

    InstructorDetail findById(int theId);

    InstructorDetail save(InstructorDetail theInstructorDetail);

    void deleteInstructorDetailById(int theId);
}
