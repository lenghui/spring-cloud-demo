package com.mysql.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.Application;
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonServiceTest {

	@Autowired
	private PersonService personService;
	
	@Test
	public void test() {
		personService.find();
	}

}
