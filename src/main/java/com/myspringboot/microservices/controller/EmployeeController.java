package com.myspringboot.microservices.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class EmployeeController {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public void getEmployee() throws IOException {
		
		List<ServiceInstance> instances = discoveryClient.getInstances("EMPLOYEE-PRODUCER");
		ServiceInstance serviceInstance = instances.get(0);
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl += "/getAllEmployeeDetails";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = null;
		
		try {
			responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
		} catch(Exception e) {
			System.out.println(e);
		}
	
		System.out.println(responseEntity.getBody());
	}

	private HttpEntity<?> getHeaders() throws IOException {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(httpHeaders);
	}
	
	
}
