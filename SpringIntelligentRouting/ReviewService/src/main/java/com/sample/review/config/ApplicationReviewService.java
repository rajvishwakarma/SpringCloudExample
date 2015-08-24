package com.sample.review.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
@EnableDiscoveryClient
@ComponentScan(basePackages={"com.sample.review.*"})
public class ApplicationReviewService {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationReviewService.class, args);
	}
	
	@RequestMapping("/abc")
	String loadURL(){
		return "abc";
	}
	
}