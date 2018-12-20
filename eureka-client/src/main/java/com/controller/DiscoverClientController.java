package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.service.ActivitiService;

@RestController
public class DiscoverClientController {

	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private ActivitiService activitiService;
	@GetMapping("/dc")
	public String discovery() {
		String services = "services:"+discoveryClient.getServices();
		System.out.println(services);
		return services;
	}
	
	@PostMapping("/jsonceshi")
	public void jsonTest(@RequestBody Map req) {
		Map<String, Object> map = (Map<String, Object>) JSONObject.parse((String) req.get("riskParam"));
		System.out.println(map);
	}
	
	@PostMapping("/activiti")
	public void activiti(@RequestBody Map req) {
		activitiService.name(req);
	}
}
