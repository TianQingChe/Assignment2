package com.yangfan.server.repository;

import com.yangfan.server.entity.Walk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.Future;

public interface WalkRepository extends CrudRepository<Walk, Long> {

        @Query(value = "select step_count from walk where user_id = ?1 and day = ?2 limit 1", nativeQuery = true)
        Integer findStepCountByUserIdAndDay(Long userId, int day);

//        Walk findByUserIdAndDay(Long userId, int day);

        @Query(value = "select step_count from walk where user_id = ?1 and day >= ?2 and day <= ?3", nativeQuery = true)
        List<Integer> selectStepCountsByUserIdAndStartDayAndNumDays(Long userId, int startDay, int endDay);
}
