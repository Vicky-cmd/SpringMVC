package com.infotrends.in;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfiguration {
	
	private Logger logger = Logger.getLogger(DataSourceConfiguration.class);


	@Value("${spring.datasource.uri}")
	private String url;
	
	@Value("${spring.datasource.port:3306}")
	private String port;
	
	@Value("${spring.datasource.username:root}")
	private String username;
	
	@Value("${spring.datasource.password:}")
	private String password;
	
	@Value("${spring.datasource.driver.classname:com.mysql.jdbc.Driver}")
	private String driverClassName;
	
	
	
	@Bean
	public DataSource dataSource() {
		
		logger.info("Creating DataSource - MYSQL  Based Configuration");
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
}
