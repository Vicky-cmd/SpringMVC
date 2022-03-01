package com.infotrends.in.Springbasics.service;

public class OutputService {

	private final TimerService timeService;
	private final GreetingService greetingService;
	private final String name;
	
	public OutputService(TimerService timeService, GreetingService greetingService, String name) {
		this.timeService=timeService;
		this.greetingService=greetingService;
		this.name=name;
	}
	
	public void printOutput() {
		System.out.println(timeService.getCurrentTime() + " - " + greetingService.getGreeting(name));
	}
}
