package com.infotrends.in.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infotrends.in.AppConfig;

@Controller
public class ViewController {
	
	@Autowired
	private ApplicationContext context;
	

	@Autowired
	private AppConfig appConfig;
	
	@RequestMapping("/")
	private String getMainPage() {
		System.out.println(String.format("Welcome to the %s!", appConfig.getAppName()));
		return "main-page";
	}
}
