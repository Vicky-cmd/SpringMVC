package com.infotrends.in.sports.annotations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class TrackWorkOutService implements WorkOutService {

    private Queue<Integer> queue = new LinkedList<>();

    @Autowired
    public TrackWorkOutService(@Qualifier("workOutMetricsQueue") Queue<Integer> queue) {
        this.queue = queue;
    }

    public int getWorkoutMetric() {
        int metric = queue.remove();
        queue.add(metric);
        return metric;
    }
}
