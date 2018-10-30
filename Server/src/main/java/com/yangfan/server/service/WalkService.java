package com.yangfan.server.service;

import com.yangfan.server.entity.Walk;
import com.yangfan.server.repository.WalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class WalkService {


    @Autowired
    private WalkRepository walkRepository;

    @Async
    @Transactional
    public Integer getDayCount(Long userId,int day) throws ExecutionException, InterruptedException {
        return this.walkRepository.findStepCountByUserIdAndDay(userId,day);
    }

    @Async
    @Transactional
    public List<Integer> getDayRangeCount(Long userId,int startDay,int numDays) throws ExecutionException, InterruptedException {
        return this.walkRepository.selectStepCountsByUserIdAndStartDayAndNumDays(userId,startDay,numDays);
    }
//
    @Async
    @Transactional
    public String addStepCount(Long userId,int day,int timeInterval,int stepCount) throws ExecutionException, InterruptedException {
        Walk newWalk = new Walk(userId,day,timeInterval,stepCount);
        Walk created = walkRepository.save(newWalk);
        return "data id:" + created.getId();
    }
}
