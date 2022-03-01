package com.infotrends.in.Springbasics.service.v2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infotrends.in.Springbasics.aspects.Countable;
import com.infotrends.in.Springbasics.aspects.Loggable;

@Service
public class GreetingService {
	
	@Value("${app.greeting}")
	private String greeting;
	
	public GreetingService() {
		
	}
	
	
	@Loggable
	@Countable
	public String getGreeting(String name) {
		return greeting + " " + name + "!";
	}

}
