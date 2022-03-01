package com.infotrends.in.Springbasics.service.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infotrends.in.Springbasics.aspects.Countable;
import com.infotrends.in.Springbasics.aspects.Trackable;
import com.infotrends.in.Springbasics.aspects.Trackable.Type;

@Service
@Trackable(Type.ALWAYS)
public class OutputService {

	@Autowired
	private TimerService timeService;
	
	@Autowired
	private GreetingService greetingService;
	
	@Value("${app.name}")
	private String name;

	@Countable
	public String printOutput() {
		return timeService.getCurrentTime() + " - " + greetingService.getGreeting(name);
	}
	
	public void printData(String data) {
		System.out.println(data);
	}
}
