package com.infotrends.in.sports.annotations.services;

import org.springframework.stereotype.Component;

@Component
public class BasketBallCoach  implements Coach {
    @Override
    public String getDailyWorkOuts() {
        return "Play basketball for atleast 1 hr every morning";
    }
}
