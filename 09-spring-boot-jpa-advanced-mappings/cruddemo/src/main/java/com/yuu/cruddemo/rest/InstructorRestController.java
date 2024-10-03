package com.yuu.cruddemo.rest;

import com.yuu.cruddemo.entity.Instructor;
import com.yuu.cruddemo.service.InstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorRestController {
    private InstructorService instructorService;

    private InstructorRestController(InstructorService theInstructorService){
        instructorService = theInstructorService;
    }

    @GetMapping("/instructors")
    public List<Instructor> findAll(){
        return instructorService.findAll();
    }

    @GetMapping("/instructors/{instructorId}")
    public Instructor getInstructor(@PathVariable int instructorId){

        Instructor theInstructor = instructorService.findById(instructorId);
        if(theInstructor == null){
            throw new RuntimeException("Instructor id not found - " + instructorId);
        }
        return theInstructor;
    }

    @PostMapping("/instructors")
    public Instructor addInstructor(@RequestBody Instructor theInstructor){
        //also just in case they pass an id in JSON ... set id to 0
        //this is to force a save of new item ... instead of update
        theInstructor.setId(0);
        Instructor dbInstructor = instructorService.save(theInstructor);
        return dbInstructor;
    }


    @PutMapping("/instructors")
    public Instructor updateInstructor(@RequestBody Instructor theInstructor){
        Instructor dbInstructor = instructorService.save(theInstructor);
        return dbInstructor;
    }

    @DeleteMapping("/instructors/{instructorId}")
    public String deleteInstructor(@PathVariable int instructorId){
       Instructor tempInstructor = instructorService.findById(instructorId);
        //throw exception if null
        if(tempInstructor == null){
            throw new RuntimeException("Instructor id not found - " + instructorId);
        }
        instructorService.deleteInstructorById(instructorId);
        return "Deleted instructor id - " + instructorId;
    }
}
