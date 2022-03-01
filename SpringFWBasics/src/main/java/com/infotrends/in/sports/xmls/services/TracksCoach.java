package com.infotrends.in.sports.xmls.services;

public class TracksCoach implements Coach{

    private WorkOutService workOutService;

    public TracksCoach(WorkOutService workOutService) {
        this.workOutService=workOutService;
    }

    @Override
    public String getDailyWorkOuts() {
        return String.format("Run for %d kms in the morning", workOutService.getWorkoutMetric());
    }
}
