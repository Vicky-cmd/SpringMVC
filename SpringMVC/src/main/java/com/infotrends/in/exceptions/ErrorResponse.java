package com.infotrends.in.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(value = Include.NON_NULL)
@Builder
public class ErrorResponse {

	private int errorCode;
	private String errorMessage;
	private String details;
	
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timestamp;
	private Map<String, String> errors;
	
	
}
