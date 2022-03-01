package com.infotrends.in.sports.annotations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    private WorkOutService workOutService;

    @Autowired
    public void setWorkOutService(@Qualifier("cricketWorkOutService") WorkOutService workOutService) {
        this.workOutService = workOutService;
    }

    @Override
    public String getDailyWorkOuts() {
        return String.format("Hit a solid %d in today's game!", workOutService.getWorkoutMetric());
    }
}
