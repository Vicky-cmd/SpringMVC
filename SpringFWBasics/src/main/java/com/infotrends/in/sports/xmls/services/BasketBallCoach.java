package com.infotrends.in.sports.xmls.services;

public class BasketBallCoach  implements Coach{
    @Override
    public String getDailyWorkOuts() {
        return "Play basketball for atleast 1 hr every morning";
    }
}
