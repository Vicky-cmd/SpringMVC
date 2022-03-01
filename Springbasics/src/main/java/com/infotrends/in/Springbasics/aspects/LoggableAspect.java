package com.infotrends.in.Springbasics.aspects;

import java.util.Arrays;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggableAspect {
	
	Logger LOGGER = LogManager.getLogger(LoggableAspect.class);
	
	@Pointcut("@annotation(Loggable)")
	public void executable() {}
	
	@Before("executable()")
	public void loggingCall(JoinPoint joinPoint) {
		StringBuilder builder = new StringBuilder("Method: ");
		builder.append(joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		if(null!=args && args.length>0) {
			builder.append(" - args [ | ");
			Arrays.stream(args).map(val -> val + " | ").forEach(builder::append);
			builder.append("]");
		}
		
		LOGGER.info(builder.toString());
	}
	
	@AfterReturning(value = "executable()", returning = "returnValue")
	public void loggingCallAfter(JoinPoint joinPoint, Object returnValue) {
		StringBuilder builder = new StringBuilder("Method: ");
		builder.append(joinPoint.getSignature().getName());
		builder.append(" - Returning");
		
		if(returnValue instanceof Collection) {
			builder.append(" : Collection of size - " + ((Collection) returnValue).size());
		} else {
			builder.append(" - " + returnValue.toString());
		}
		
		LOGGER.info(builder.toString());
	}
}
