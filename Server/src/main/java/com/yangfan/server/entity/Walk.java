package com.yangfan.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "walk")
public class Walk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private int day;

    @Column(name = "time_interval")
    private int timeInterval;

    @Column(name = "step_count")
    private int stepCount;

    public Walk(){

    }

    public Walk(Long userId, int day, int timeInterval, int stepCount) {
        this.userId = userId;
        this.day = day;
        this.timeInterval = timeInterval;
        this.stepCount = stepCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }
}
