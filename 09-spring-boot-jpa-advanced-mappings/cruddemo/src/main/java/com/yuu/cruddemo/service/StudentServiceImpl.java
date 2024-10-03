package com.yuu.cruddemo.service;

import com.yuu.cruddemo.entity.Student;
import com.yuu.cruddemo.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository theStudentRepository){
        studentRepository = theStudentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);
        Student theStudent = null;
        if (result.isPresent()){
            theStudent = result.get();
        }
        else{
            throw new RuntimeException("Student id not found - " + theId);
        }
        return theStudent;
    }

    @Override
    public Student save(Student theStudent) {
        return studentRepository.save(theStudent);
    }

    @Override
    public void deleteStudentById(int theId) {
        studentRepository.deleteById(theId);
    }
}
