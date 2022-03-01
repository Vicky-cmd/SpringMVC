package com.infotrends.in.Springbasics.aspects;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CountableAspect {

	
	private final Logger LOGGER = LogManager.getLogger(CountableAspect.class);
	private Map<String, Integer> counterMap = new HashMap<>();
	
	private int incrementCounter(String methodName) {
		int intCounter = 1;
		if(counterMap.containsKey(methodName)) {
			intCounter+=counterMap.get(methodName);
		}
		
		counterMap.put(methodName, intCounter);
		return intCounter;
	}
	
	@Pointcut("@annotation(Countable)")
	public void executeCounter() {}
	
	
	@Around(value="executeCounter()")
	public Object executeCounter(ProceedingJoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getDeclaringType().toString().concat("." + joinPoint.getSignature().getName());
		LOGGER.info("Method : " + methodName + " - Counter: " + incrementCounter(methodName));
		
		Object returnValue = joinPoint.proceed();
		return returnValue;
	}
	
	
}
