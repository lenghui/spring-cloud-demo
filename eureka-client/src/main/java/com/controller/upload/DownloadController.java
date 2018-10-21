package com.controller.upload;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.DownloadService;

@RestController
@RequestMapping("/download")
public class DownloadController {

	private boolean FLAG = false;
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	@Autowired
	private DownloadService downloadService;
	
	@GetMapping("/down")
	public void download(HttpServletResponse response) {
		try {
			FLAG = downloadService.downLoad("E:\\workspace_mytest\\eureka-client\\temp\\acct.sql", response, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/downCheck")
	public Map<String, Object> downloadCheck(@RequestParam("username") String username) {
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(FLAG);
		if(FLAG) {
			result.put("message", "下载成功！");
		}else {
			result.put("message", "下载失败！");
		}
		FLAG = false;
		return result;
	}

}
