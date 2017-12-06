package com.in28minutes.spring.basics.soapcoursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseDetailsRequest;
import com.in28minutes.courses.DeleteCourseDetailsResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.courses.Status;
import com.in28minutes.spring.basics.soapcoursemanagement.soap.bean.Course;
import com.in28minutes.spring.basics.soapcoursemanagement.soap.exception.CourseNotFoundException;
import com.in28minutes.spring.basics.soapcoursemanagement.soap.service.CourseDetailsService;


	
/// you need to expose this endpoint to a url

@Endpoint
public class CourseDetailsEndpoint {
	
	@Autowired
	CourseDetailsService service;
	
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request){
		
		Course course = service.findbyId(request.getId());
		if (course == null) {
			new CourseNotFoundException("Invalid Course Id" + request.getId());
		}
		return mapCourseDetails(course);
		
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}
	
	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request){
		
		List<Course> courses = service.findAll();
		
		return this.mapAllCourseDetails(courses);
		
	}

	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request){
		
		int queue = service.deletebyId(request.getId());
		
		return this.mapDelete(queue);
		
	}
	
	
	private com.in28minutes.courses.DeleteCourseDetailsResponse mapDelete(int id){
		DeleteCourseDetailsResponse deleteCourseDetailsResponse = new DeleteCourseDetailsResponse();
		if (id== 1) {
			deleteCourseDetailsResponse.setStatus(Status.SUCCESS);
		}
		else {
			deleteCourseDetailsResponse.setStatus(Status.FAILURE);
		}
		return deleteCourseDetailsResponse;
	}
	
	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();	
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}
}
