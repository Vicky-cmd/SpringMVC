package com.infotrends.in.Springbasics.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.infotrends.in.Springbasics.service.GreetingService;
import com.infotrends.in.Springbasics.service.OutputService;
import com.infotrends.in.Springbasics.service.TimerService;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	
	@Value("${app.greeting}")
	private String greeting;

	@Value("${app.name}")
	private String name;
	
	@Value("#{new Boolean(environment['spring.profiles.active']!='dev')}")
	private boolean is24hrFormat;
	
	
	@Bean
	public TimerService getTimerService() {
		return new TimerService(is24hrFormat);
	}
	
	@Bean
	public GreetingService getGreetingService() {
		return new GreetingService(greeting);
	}
	
	@Bean
	public OutputService getOutputService(@Autowired TimerService timerService,
			@Autowired GreetingService greetingService) {
		return new OutputService(timerService, greetingService, name);
	}
	
	

}
