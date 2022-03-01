package com.infotrends.in.Springbasics.service.v2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TimerService {

	private DateTimeFormatter formatter;
	
	@Value("#{new Boolean(environment['spring.profiles.active']!='dev')}")
	private boolean is24HrFormat;
	
	public TimerService() {
		
	}
	
	public String getCurrentTime() {
		String pattern ="dd-MM-yyyy ";
		pattern+=is24HrFormat?"HH:mm:ss":"hh:mm:ss a";
		formatter=DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
		LocalDateTime time = LocalDateTime.now();
		return time.format(formatter);
	}
}
