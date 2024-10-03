package com.yuu.cruddemo.service;


import com.yuu.cruddemo.entity.InstructorDetail;
import com.yuu.cruddemo.repo.InstructorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorDetailServiceImpl implements InstructorDetailService {

    private InstructorDetailRepository instructorDetailRepository;

    @Autowired
    public InstructorDetailServiceImpl(InstructorDetailRepository theInstructorDetailRepository){
        instructorDetailRepository = theInstructorDetailRepository;
    }

    @Override
    public List<InstructorDetail> findAll() {
        return instructorDetailRepository.findAll();
    }

    @Override
    public InstructorDetail findById(int theId) {
        Optional<InstructorDetail> result = instructorDetailRepository.findById(theId);
        InstructorDetail theInstructorDetail = null;
        if (result.isPresent()){
            theInstructorDetail = result.get();
        }
        else{
            throw new RuntimeException("Instructor detail id not found - " + theId);
        }
        return theInstructorDetail;
    }

    @Override
    public InstructorDetail save(InstructorDetail theInstructorDetail) {
        return instructorDetailRepository.save(theInstructorDetail);
    }

    @Override
    public void deleteInstructorDetailById(int theId) {
        instructorDetailRepository.deleteById(theId);
    }
}
