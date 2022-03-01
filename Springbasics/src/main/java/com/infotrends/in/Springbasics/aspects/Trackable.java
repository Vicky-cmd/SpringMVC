package com.infotrends.in.Springbasics.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Trackable {

	Type value() default Type.ALWAYS;
	
	public static enum Type {
		ALWAYS,
		
		CLASS_LEVEL,
		
		METHOD_LEVEL
	}
}
