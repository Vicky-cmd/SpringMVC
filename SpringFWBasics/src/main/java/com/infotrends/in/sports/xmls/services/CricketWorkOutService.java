package com.infotrends.in.sports.xmls.services;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CricketWorkOutService implements WorkOutService {

    private List<Integer> metricsList = new LinkedList<>();

    public CricketWorkOutService(List<Integer> metricsList) {
        this.metricsList = metricsList;
    }

    @Override
    public int getWorkoutMetric() {
        int metric = metricsList.get(ThreadLocalRandom.current().nextInt(0, metricsList.size()));
        return metric * ThreadLocalRandom.current().nextInt(3, 8);
    }
}
