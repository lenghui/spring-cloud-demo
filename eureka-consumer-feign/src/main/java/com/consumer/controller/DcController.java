package com.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feign.inter.DcInter;

@RestController
public class DcController {

	@Autowired
	DcInter dcInter;
	
	@GetMapping("/consumer")
	public String dc() {
		return dcInter.consumer();
	}
}
