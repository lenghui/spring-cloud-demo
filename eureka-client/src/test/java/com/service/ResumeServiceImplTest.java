package com.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResumeServiceImplTest {

	   @Autowired
	    RuntimeService runtimeService;

	    @Test
	    public void TestStartProcess() {
	        Map<String, Object> variables = new HashMap<String, Object>();
	        variables.put("applicantName", "John Doe");
	        variables.put("email", "john.doe@activiti.com");
	        variables.put("phoneNumber", "123456789");
	        runtimeService.startProcessInstanceByKey("myProcess", variables);
	    }
}
