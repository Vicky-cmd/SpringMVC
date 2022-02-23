package com.infotrends.in.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HiberbateUtils {

	@Autowired
	private SessionFactory factory;
	
	
	public Session getCurrentSession() {
		return factory.getCurrentSession();
	}
	

	public Session openSession() {
		return factory.openSession();
	}
	
	
	
	
}
