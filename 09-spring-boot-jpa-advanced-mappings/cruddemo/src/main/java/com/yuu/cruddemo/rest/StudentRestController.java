package com.yuu.cruddemo.rest;

import com.yuu.cruddemo.entity.Student;
import com.yuu.cruddemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private StudentService studentService;

    private StudentRestController(StudentService theStudentService){
        studentService = theStudentService;
    }

    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        Student theStudent = studentService.findById(studentId);
        if(theStudent == null){
            throw new RuntimeException("Student id not found - " + studentId);
        }
        return theStudent;
    }

    @PostMapping("/students")
    public Student addCourse(@RequestBody Student theStudent){
        //also just in case they pass an id in JSON ... set id to 0
        //this is to force a save of new item ... instead of update
        theStudent.setId(0);
        Student dbStudent = studentService.save(theStudent);
        return dbStudent;
    }


    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent){
        Student dbStudent = studentService.save(theStudent);
        return dbStudent;
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        Student tempStudent =  studentService.findById(studentId);
        //throw exception if null
        if(tempStudent == null){
            throw new RuntimeException("Student id not found - " + studentId);
        }
        studentService.deleteStudentById(studentId);
        return "Deleted student id - " + studentId;
    }
}
