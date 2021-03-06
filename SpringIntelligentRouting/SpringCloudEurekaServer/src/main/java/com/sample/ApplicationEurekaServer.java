package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class ApplicationEurekaServer {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEurekaServer.class, args);
	}
}
