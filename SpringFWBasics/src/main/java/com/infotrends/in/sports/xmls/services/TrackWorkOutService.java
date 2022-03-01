package com.infotrends.in.sports.xmls.services;

import java.util.LinkedList;
import java.util.Queue;

public class TrackWorkOutService implements WorkOutService{

    private Queue<Integer> queue = new LinkedList<>();

    public TrackWorkOutService(Queue<Integer> queue) {
        this.queue = queue;
    }

    public int getWorkoutMetric() {
        int metric = queue.remove();
        queue.add(metric);
        return metric;
    }
}
