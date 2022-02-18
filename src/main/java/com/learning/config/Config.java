package com.learning.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import com.learning.utils.PasswordUtils;

@Configuration
//@ComponentScan("com.zee.zee5app")
//@PropertySource("classpath:application.properties")
public class Config {
	@Autowired
    Environment environment;
	
//	@Bean(name="ds")
//	@Scope("singleton")
//	public DataSource dataSource() {
//		
//		BasicDataSource basicDataSource=new BasicDataSource();
//		basicDataSource.setUsername(environment.getProperty("jdbc.username"));
//		basicDataSource.setPassword(environment.getProperty("jdbc.password"));
//		basicDataSource.setUrl(environment.getProperty("jdbc.url"));
//		basicDataSource.setDefaultAutoCommit(false);
//		try {
//			basicDataSource.getConnection().setAutoCommit(false);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return basicDataSource;
//		}
	
		@Bean
		public PasswordUtils passwordUtils() {
			return new PasswordUtils();
		}
	}

