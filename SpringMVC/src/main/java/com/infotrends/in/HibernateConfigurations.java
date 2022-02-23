package com.infotrends.in;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.infotrends.in.model.User;


@Configuration
@EnableTransactionManagement
public class HibernateConfigurations {
    
    @Autowired
    private DataSource dataSource;
    
    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String show_sql;

    @Value("${hibernate.format_sql}")
    private String format_sql;
    
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Value("${hibernate.connection.pool_size}")
    private String pool_size;

    @Value("${hibernate.current_session_context_class}")
    private String current_session_context_class;


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", show_sql);
        properties.put("hibernate.format_sql", format_sql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        properties.put("hibernate.connection.pool_size", pool_size);
        properties.put("current_session_context_class", current_session_context_class);
        return properties;
    }
	
    @Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.infotrends.in.model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.afterPropertiesSet();
		SessionFactory factory =sessionFactory.getObject();
		return factory;
	}
    
//    @Bean
//	public SessionFactory sessionFactory() {
//    	SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
//    			.configure("hibernate.cfg.xml")
//    			.setPackagesToScan("com.infotrends.in.model")
//    			.addAnnotatedClass(User.class)
//    			.buildSessionFactory(); 
//		return sessionFactory;
//	}
	

    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
    	HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    	transactionManager.setSessionFactory(sessionFactory());
    	
    	return transactionManager;
    }
	
//    @Bean
//    @DependsOn(value = {"transactionManager", "sessionFactory"})
//    public Transaction transaction() throws IOException {
//    	SessionFactory factory = sessionFactory();
//    	return factory.getCurrentSession().
//    }
    
}
