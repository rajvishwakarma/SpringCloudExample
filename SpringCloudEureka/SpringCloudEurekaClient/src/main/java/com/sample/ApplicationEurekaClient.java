package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
public class ApplicationEurekaClient {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEurekaClient.class, args);
	}
	
	@RequestMapping("/abc")
	String loadURL(){
		return "abc";
	}
	
}