package com.infotrends.in.sports.annotations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.*;

@ComponentScan("com.infotrends.in.sports.annotations")
@PropertySource("classpath:sports-properties.properties")
public class ApplicationConfigurations {
}
