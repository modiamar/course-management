package com.in28minutes.spring.basics.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
// Spring Configuration File
//This is config file
@Configuration
public class WebServiceConfig {

	//MessageDispatcherServlet
	
	//SpringApplicationContext
	
	////url -> /ws/courses.wsdl
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context){
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		//Uses your defined applicationcontext
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		//Mapped to this URL
		return new ServletRegistrationBean(messageDispatcherServlet,"/ws/*");
		
	}
	
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWdl11Definition(XsdSchema coursesSchema){
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		//PortType - CoursePort
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://in28minutes.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema);
		//Namespace - http://in28minutes.com/courses
		// /ws
		// schema
		
		
		return definition;
	}
	
	@Bean
	public XsdSchema coursesSchema(){
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
	//wsdl courses.wsdl expose to url /ws/courses.wsdl
		
	//course-details.xsd
	
	
}
