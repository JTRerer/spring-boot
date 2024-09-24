package com.yuu.springcoredemo.rest;

import com.yuu.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define a private field for the dependency
    private Coach myCoach;

    @Autowired
    public void setCoach(Coach theCoach){
        myCoach = theCoach;
    }

    //define mapping for "dailyworkout"
    @GetMapping ("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
