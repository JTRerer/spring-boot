package com.yuu.cruddemo.rest;

import com.yuu.cruddemo.entity.InstructorDetail;
import com.yuu.cruddemo.service.InstructorDetailService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorDetailRestController {
    private InstructorDetailService instructorDetailService;

    private InstructorDetailRestController(InstructorDetailService theInstructorDetailService){
        instructorDetailService = theInstructorDetailService;
    }

    @GetMapping("/instructorDetails")
    public List<InstructorDetail> findAll(){
        return instructorDetailService.findAll();
    }

    @GetMapping("/instructorDetails/{instructorDetailId}")
    public InstructorDetail getInstructor(@PathVariable int instructorDetailId){

        InstructorDetail theInstructorDetail = instructorDetailService.findById(instructorDetailId);
        if(theInstructorDetail == null){
            throw new RuntimeException("Instructor detail id not found - " + instructorDetailId);
        }
        return theInstructorDetail;
    }

    @PostMapping("/instructorDetails")
    public InstructorDetail addInstructorDetail(@RequestBody InstructorDetail theInstructorDetail){
        //also just in case they pass an id in JSON ... set id to 0
        //this is to force a save of new item ... instead of update
        theInstructorDetail.setId(0);
        InstructorDetail dbInstructorDetail = instructorDetailService.save(theInstructorDetail);
        return dbInstructorDetail;
    }


    @PutMapping("/instructorDetails")
    public InstructorDetail updateInstructorDetail(@RequestBody InstructorDetail theInstructorDetail){
        InstructorDetail dbInstructorDetail = instructorDetailService.save(theInstructorDetail);
        return dbInstructorDetail;
    }

    @DeleteMapping("/instructorDetails/{instructorDetailId}")
    public String deleteInstructorDetail(@PathVariable int instructorDetailId){
        InstructorDetail tempInstructorDetail = instructorDetailService.findById(instructorDetailId);
        //throw exception if null
        if(tempInstructorDetail == null){
            throw new RuntimeException("Instructor detail id not found - " + instructorDetailId);
        }
        instructorDetailService.deleteInstructorDetailById(instructorDetailId);
        return "Deleted instructor detail id - " + instructorDetailId;
    }
}
