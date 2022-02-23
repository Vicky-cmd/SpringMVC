package com.infotrends.in;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class DefaultConfigurations {

	
	@Bean
	public ObjectMapper objectMapper() {
		JsonFactory factory = new JsonFactory();
		factory.configure(Feature.IGNORE_UNKNOWN, true);
		
		ObjectMapper objectMapper = new ObjectMapper(factory);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		
		return objectMapper;
	}
}
