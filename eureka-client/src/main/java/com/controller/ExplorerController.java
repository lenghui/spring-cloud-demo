package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.SmsPropConfig;

@RestController
@RequestMapping("/explor")
public class ExplorerController {
	
	@Autowired
	private SmsPropConfig smspropConfig;
	
	@PostMapping("/smsceshi")
	public Map getSms(@RequestBody Map req) {
//		Map head = (Map) req.get("header");
//		Map bodyMap = (Map) req.get("body");
//		return "head="+JSONObject.toJSONString(head)+", body"+JSONObject.toJSONString(bodyMap);
		String str = smspropConfig.getTemp();
		return req;
	}
	
}
