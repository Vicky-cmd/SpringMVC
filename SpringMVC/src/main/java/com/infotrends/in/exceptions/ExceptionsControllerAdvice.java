package com.infotrends.in.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(basePackages = {"com.infotrends.in.controllers"})
public class ExceptionsControllerAdvice {

	@ExceptionHandler(value = {BusinessExceptions.class})
	public ResponseEntity<ErrorResponse> handleExceptions(BusinessExceptions ex, WebRequest request) {
		ErrorResponse error = ErrorResponse.builder().errorCode(ex.getStatus().value())
					 .errorMessage(ex.getMessage())
					 .details(ex.getDetails())
					 .timestamp(LocalDateTime.now())
					 .build();
		
		return new ResponseEntity<ErrorResponse>(error, ex.getStatus());
	}
	
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorResponse> handleExceptions(MethodArgumentNotValidException ex, WebRequest request) {

		Map<String, String> errorDetails = ex.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(
						FieldError::getField, 
						v -> v.getDefaultMessage(),
						(existing, replacement) -> {
							return existing.join(", ", replacement);
						}
						));
		
		ErrorResponse error =  ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value())
				 .errors(errorDetails)
				 .timestamp(LocalDateTime.now())
				 .build();
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	public ResponseEntity<ErrorResponse> handleExceptions(HttpMessageNotReadableException ex, WebRequest request) {

		ErrorResponse error = ErrorResponse.builder().errorCode(HttpStatus.BAD_REQUEST.value())
				 .errorMessage("Unresolved_Errors")
				 .details("There are unresolvable errors in your request")
				 .timestamp(LocalDateTime.now())
				 .build();
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}	
}
