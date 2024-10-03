package com.yuu.cruddemo.service;

import com.yuu.cruddemo.repo.InstructorRepository;
import com.yuu.cruddemo.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements InstructorService{
    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository theInstructorRepository){
        instructorRepository = theInstructorRepository;
    }

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(int theId) {
        Optional<Instructor> result = instructorRepository.findById(theId);
        Instructor theInstructor = null;
        if (result.isPresent()){
            theInstructor = result.get();
        }
        else{
            throw new RuntimeException("Instructor id not found - " + theId);
        }
        return theInstructor;
    }

    @Override
    public Instructor save(Instructor theInstructor) {
        return instructorRepository.save(theInstructor);
    }

    @Override
    public void deleteInstructorById(int theId) {
        instructorRepository.deleteById(theId);
    }
}
