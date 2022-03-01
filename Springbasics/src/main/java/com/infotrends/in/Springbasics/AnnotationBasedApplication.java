package com.infotrends.in.Springbasics;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.infotrends.in.Springbasics.aspects.Countable;
import com.infotrends.in.Springbasics.config.ApplicationAnnotationConfig;
import com.infotrends.in.Springbasics.service.v2.GreetingService;
import com.infotrends.in.Springbasics.service.v2.OutputService;
import com.infotrends.in.Springbasics.service.v2.TimerService;

public class AnnotationBasedApplication 
{
	
	@Countable
    public static void main( String[] args ) throws InterruptedException
    {
    	
    	ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationAnnotationConfig.class);
    	
    	GreetingService greetingService = context.getBean(GreetingService.class);
    	TimerService timeService = context.getBean(TimerService.class);
    	OutputService outputService = context.getBean(OutputService.class);
    	
    	for(int i=0; i<5; i++) {
    		outputService.printData(outputService.printOutput());
    		Thread.sleep(1000);
    	}
    }
}
