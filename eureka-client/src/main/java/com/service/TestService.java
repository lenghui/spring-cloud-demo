package com.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestService implements JavaDelegate {
	
	private static final Logger logger = LoggerFactory.getLogger(TestService.class);


	@Override
	public void execute(DelegateExecution execution) {
		logger.info("启动工作流");
		execution.setVariable("result", "SUCCESS");
		
	}
}
