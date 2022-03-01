package com.infotrends.in.sports.annotations.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
@Profile("dev")
public class DevConfigurations {

    @PostConstruct
    void init() {
        System.out.println("Using the Dev Configurations............");
    }

    @Value("#{{25,30,23,15,20,28,26}}")
    private Collection<Integer> cricketMetrics;

    @Value("#{{5,6,9,10,10,11,11}}")
    private Collection<Integer> workOutMetrics;

    @Bean
    public List<Integer> cricketMetricsList() {
        return new LinkedList<>(cricketMetrics);
    }

    @Bean
    public Queue<Integer> workOutMetricsQueue() {
        return new LinkedList<>(workOutMetrics);
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
