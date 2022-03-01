package com.infotrends.in.sports.annotations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TracksCoach implements Coach {

    private WorkOutService workOutService;

    @Autowired
    public TracksCoach(@Qualifier("trackWorkOutService") WorkOutService workOutService) {
        this.workOutService=workOutService;
    }

    @Override
    public String getDailyWorkOuts() {
        return String.format("Run for %d kms in the morning", workOutService.getWorkoutMetric());
    }
}
