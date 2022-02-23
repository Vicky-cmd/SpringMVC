package com.infotrends.in.validations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidateEnums, String>{

	List<String> validArgs = new ArrayList<String>();
	String errorMessage;
	boolean mandatory;
	
	@Override
	public void initialize(ValidateEnums constraintAnnotation) {
		validArgs.addAll(Arrays.asList(constraintAnnotation.value().split(constraintAnnotation.delimiter())));
		errorMessage=constraintAnnotation.message();
		mandatory=constraintAnnotation.mandatory();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
//		context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
		if(!mandatory && validArgs.size()==0) return true;
		
		return validArgs.contains(value);
	}

}
