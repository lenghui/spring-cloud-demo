package com.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.service.ConsumerService;

@RestController
public class DcController {

	@Autowired
	private ConsumerService consumerService;
	
	@GetMapping("/consumer")
	public String dc() {

		return consumerService.consumer();
	}
}
