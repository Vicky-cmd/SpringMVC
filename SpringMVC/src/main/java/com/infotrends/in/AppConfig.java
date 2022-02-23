package com.infotrends.in;

import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@Scope("singleton")
public class AppConfig {
	
	private Logger logger = Logger.getLogger(AppConfig.class);

	@Value("${app.name}")
	private String appName;
	
	@Autowired(required = false)
	private SessionFactory factory;
	
	
	@PreDestroy
	private void releaseExpensiveResources() {
		logger.info("Releasing the Expensive Resources");
		if(factory!=null) factory.close();
	}
	
	
}
