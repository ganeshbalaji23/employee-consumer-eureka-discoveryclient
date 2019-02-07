package com.myspringboot.microservices;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClientException;

import com.myspringboot.microservices.controller.EmployeeController;

@SpringBootApplication
public class EmployeeConsumerApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx = SpringApplication.run(EmployeeConsumerApplication.class, args);
		
		EmployeeController employeeController = ctx.getBean(EmployeeController.class);
		employeeController.getEmployee();
	}
	
	@Bean
	public EmployeeController employeeController() {
		return new EmployeeController();
	}

}

