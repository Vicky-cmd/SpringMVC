package com.infotrends.in.sports.xmls;

import com.infotrends.in.sports.xmls.services.AppConfig;
import com.infotrends.in.sports.xmls.services.Coach;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class SportsApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("sports-app-context.xml");

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
