package com.sample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.sample.*"})
public class ApplicationClient {

	@Value("${config}")
	public String config;

	@Value("${otherDesc}")
	public String otherDesc;

	@Bean(name="configuration")
	String configure(){
		return "Config : "+config + " Desc : "+otherDesc;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationClient.class, args);
	}
}