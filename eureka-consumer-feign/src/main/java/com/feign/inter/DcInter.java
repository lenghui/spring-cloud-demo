package com.feign.inter;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface DcInter {
	@GetMapping("/dc")
	public String consumer();
}
