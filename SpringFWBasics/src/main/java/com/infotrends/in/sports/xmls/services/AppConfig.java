package com.infotrends.in.sports.xmls.services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppConfig {

    void init() {
        System.out.println(String.format("Inside the init method of %s class", this.getClass().getCanonicalName()));
        System.out.println(this.coachType);
    }

    private String coachType;
}
