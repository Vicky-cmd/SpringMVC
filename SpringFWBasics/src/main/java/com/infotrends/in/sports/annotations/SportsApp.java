package com.infotrends.in.sports.annotations;

import com.infotrends.in.sports.annotations.services.AppConfig;
import com.infotrends.in.sports.annotations.services.ApplicationConfigurations;
import com.infotrends.in.sports.annotations.services.Coach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class SportsApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigurations.class);

        AppConfig config = context.getBean("appConfig", AppConfig.class);

        Coach coach = context.getBean(config.getCoachType(), Coach.class);

        Scanner sc = context.getBean("scanner", Scanner.class);

        while(true) {
            System.out.println(coach.getDailyWorkOuts());
            System.out.println("Enter Y to exit");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }

    }
}
