package com.in28minutes.spring.basics.soapcoursemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

//Spring Boot automatically scans the package where the main application is present

@SpringBootApplication
public class CourseManagementApplication {

	public static void main(String[] args) {
		
		//beans
		//what are the depednecies
		// Application Context maintains all beans

		
		
		ApplicationContext run = SpringApplication.run(CourseManagementApplication.class, args);
	}
}
