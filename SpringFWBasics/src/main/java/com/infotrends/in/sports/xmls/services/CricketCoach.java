package com.infotrends.in.sports.xmls.services;

public class CricketCoach implements Coach{

    private WorkOutService workOutService;

    public void setWorkOutService(WorkOutService workOutService) {
        this.workOutService = workOutService;
    }

    @Override
    public String getDailyWorkOuts() {
        return String.format("Hit a solid %d in today's game!", workOutService.getWorkoutMetric());
    }
}
