package com.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dao.DummyDao;


@RestController
public class DemoController {
	
	@Autowired
	DummyDao dao;
	
	@Autowired
	@Qualifier(value="configuration")
	String configuration;
	
	@RequestMapping("/")
	public String home() {
		System.out.println(configuration);
	    return configuration;
	}
	
	 @RequestMapping("/someAction")
	 public String someAction() {
	      return "Some Action";
     }
	
}