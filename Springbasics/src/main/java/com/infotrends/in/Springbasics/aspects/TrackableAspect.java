package com.infotrends.in.Springbasics.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TrackableAspect {

	Logger LOGGER = LogManager.getLogger(TrackableAspect.class);
	
//	@Pointcut("@annotation(Trackable) && !@within(com.infotrends.in.Springbasics.aspects.Trackable)")
//	public void executeAspect() {}
	
//	@Around(value="executeAspect()")
//	public Object executeAroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
//		String methodName = joinPoint.getSignature().getDeclaringType().toString().concat("." + joinPoint.getSignature().getName());
//		LOGGER.info("Method : " + methodName);
//		LOGGER.info("TRACKING CALL");
//		
//		Object returnValue = joinPoint.proceed();
//		return returnValue;
//	}
	

	@Around(value="@annotation(trackable) && !@within(com.infotrends.in.Springbasics.aspects.Trackable)", argNames = "trackable")
	public Object executeAroundAspect(ProceedingJoinPoint joinPoint, Trackable trackable) throws Throwable {
		String methodName = joinPoint.getSignature().getDeclaringType().toString().concat("." + joinPoint.getSignature().getName());
		LOGGER.info("Method : " + methodName);
		LOGGER.info("TRACKING CALL");
		
		Object returnValue = joinPoint.proceed();
		return returnValue;
	}
	

	@Around(value="@within(trackable)", argNames = "trackable")
	public Object executeAroundAspect2(ProceedingJoinPoint joinPoint, Trackable trackable) throws Throwable {
		String methodName = joinPoint.getSignature().getDeclaringType().toString().concat("." + joinPoint.getSignature().getName());
		LOGGER.info("Method : " + methodName);
		LOGGER.info("TRACKING CALL");
		
		Object returnValue = joinPoint.proceed();
		return returnValue;
	}
	
}
