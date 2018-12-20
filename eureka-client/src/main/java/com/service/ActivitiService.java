package com.service;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiService {

	
	private static final Logger logger = LoggerFactory.getLogger(ActivitiService.class);

	@Autowired
	private RuntimeService runtimeService;
	
	private static String BIZ_KEY_KEY_NAME = "liuchengtuceshi";
	
	public void name(Map req) {
		String key = "myProcess";
		String bizKey = "apply1";
		
		logger.info("开始工作流", "{" + key + "} +, {"+bizKey+"}");

		ProcessInstanceBuilder builder = runtimeService.createProcessInstanceBuilder().processDefinitionKey(key).businessKey(bizKey);
		builder.variable(BIZ_KEY_KEY_NAME, bizKey);
		
		ProcessInstance pi = builder.start();
		
		logger.info(pi.getId(),pi.getProcessInstanceId());
		
	}
}
