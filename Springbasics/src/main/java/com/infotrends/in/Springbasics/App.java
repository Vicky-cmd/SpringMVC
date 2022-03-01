package com.infotrends.in.Springbasics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotrends.in.Springbasics.config.ApplicationConfig;
import com.infotrends.in.Springbasics.service.GreetingService;
import com.infotrends.in.Springbasics.service.OutputService;
import com.infotrends.in.Springbasics.service.TimerService;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws InterruptedException
    {
    	
    	ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    	
    	GreetingService greetingService = context.getBean(GreetingService.class);
    	TimerService timeService = context.getBean(TimerService.class);
    	OutputService outputService = context.getBean(OutputService.class);
    	
    	for(int i=0; i<5; i++) {
    		outputService.printOutput();
    		Thread.sleep(1000);
    	}
    }
}
