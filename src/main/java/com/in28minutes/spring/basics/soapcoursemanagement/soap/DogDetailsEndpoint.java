package com.in28minutes.spring.basics.soapcoursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DogDetails;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.courses.GetDogDetailsRequest;
import com.in28minutes.courses.GetDogDetailsResponse;


/// you need to expose this endpoint to a url

@Endpoint
public class DogDetailsEndpoint {
	
	@PayloadRoot(namespace = "http://in28minutes.com/courses", localPart="GetDogDetailsRequest")
	@ResponsePayload
	public GetDogDetailsResponse processCourseDetailsRequest(@RequestPayload GetDogDetailsRequest request){
		GetDogDetailsResponse response = new GetDogDetailsResponse();
		DogDetails dogDetails = new DogDetails();
		dogDetails.setId(request.getId());
		dogDetails.setName("Mircrfvbe coruse");
		dogDetails.setDescription("FREFREREF");
		response.setDogDetails(dogDetails);
		return response;
		
	}
}
