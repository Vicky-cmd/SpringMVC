package com.infotrends.in.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateEnums {


	String value();
	String delimiter() default ",";
	String message() default "Please enter a valid result";
	boolean mandatory() default false;
	
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
	
}
