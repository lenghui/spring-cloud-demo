package com.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ConsumerService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="fallBack")
	public String consumer() {
		return restTemplate.getForObject("http://eureka-client/dc", String.class);
	}
	
	public String fallBack() {
		return "fallBack";
	}
	
}
