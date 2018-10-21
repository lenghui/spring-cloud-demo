package com.demo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HelloWorld {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/helloworld")
	public String sayHello() {
		return "hello world";
	}
}
