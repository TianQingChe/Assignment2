package com.yangfan.server.controller;

import com.yangfan.server.entity.Walk;
import com.yangfan.server.repository.WalkRepository;
import com.yangfan.server.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class WalkController {

    @Autowired
    private WalkService walkService;

    @GetMapping("/")
    public String welcome(){
        return "Welcome";
    }

    @PostMapping("/{userId}/{day}/{timeInterval}/{stepCount}")
    public String addStepCount(@PathVariable(value = "userId") Long userId,
                               @PathVariable(value = "day") int day,
                               @PathVariable(value = "timeInterval") int timeInterval,
                               @PathVariable(value = "stepCount") int stepCount) throws ExecutionException, InterruptedException {

        return walkService.addStepCount(userId,day,timeInterval,stepCount);
    }

    @GetMapping("/current/{userID}")
    public Integer getCurrentDayCount(@PathVariable(value = "userID") Long userId) throws ExecutionException, InterruptedException {
        return walkService.getDayCount(userId, 1);
    }

    @GetMapping("/single/{userID}/{day}")
    public Integer getDayCount(@PathVariable(value = "userID") Long userId,
                              @PathVariable(value = "day") int day) throws ExecutionException, InterruptedException {
        return walkService.getDayCount(userId,day);
    }


    @GetMapping("/range/{userID}/{startDay}/{numDays}")
    public List<Integer> getDayRangeCount(@PathVariable(value = "userID") Long userId,
                                          @PathVariable(value = "startDay") int startDay,
                                          @PathVariable(value = "numDays") int numDays) throws ExecutionException, InterruptedException {
        return walkService.getDayRangeCount(userId, startDay, startDay + numDays - 1);
    }
}
