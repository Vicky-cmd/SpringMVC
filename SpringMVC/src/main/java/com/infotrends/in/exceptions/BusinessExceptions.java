package com.infotrends.in.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessExceptions extends Exception {

	private static final long serialVersionUID = -3320469917435717154L;
	private HttpStatus status;
	private String message;
	private String details;
	
	public BusinessExceptions(HttpStatus status, String message, String details) {
		super(message);
		this.status = status;
		this.message = message;
		this.details=details;
	}
	
	
}
