package com.infotrends.in.Springbasics.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimerService {

	private final DateTimeFormatter formatter;
	
	public TimerService(boolean is24HrFormat) {
		String pattern ="dd-MM-yyyy ";
		pattern+=is24HrFormat?"HH:mm:ss":"hh:mm:ss a";
		formatter=DateTimeFormatter.ofPattern(pattern, Locale.getDefault());
	}
	
	public String getCurrentTime() {
		LocalDateTime time = LocalDateTime.now();
		return time.format(formatter);
	}
}
