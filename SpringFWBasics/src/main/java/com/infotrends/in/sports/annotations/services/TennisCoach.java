package com.infotrends.in.sports.annotations.services;

import org.springframework.stereotype.Component;

@Component("tennisCoach")
public class TennisCoach implements Coach {

    @Override
    public String getDailyWorkOuts() {
        return "Practice your backhand volley";
    }
}
