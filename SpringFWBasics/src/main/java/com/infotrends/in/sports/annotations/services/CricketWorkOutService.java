package com.infotrends.in.sports.annotations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CricketWorkOutService implements WorkOutService {

    private List<Integer> metricsList = new LinkedList<>();

    @Autowired
    public CricketWorkOutService(@Qualifier("cricketMetricsList") List<Integer> metricsList) {
        this.metricsList = metricsList;
    }

    @Override
    public int getWorkoutMetric() {
        int metric = metricsList.get(ThreadLocalRandom.current().nextInt(0, metricsList.size()));
        return metric * ThreadLocalRandom.current().nextInt(3, 8);
    }
}
