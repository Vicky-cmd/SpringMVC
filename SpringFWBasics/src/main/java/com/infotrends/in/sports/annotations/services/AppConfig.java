package com.infotrends.in.sports.annotations.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Getter
@Setter
@Component
public class AppConfig {

    @PostConstruct
    void init() {
        System.out.println(String.format("Inside the init method of %s class", this.getClass().getCanonicalName()));
        System.out.println(this.coachType);
    }

    @Value("${coach.type}")
    private String coachType;

}
