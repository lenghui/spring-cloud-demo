package com.controller.upload;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.DownloadService;

@RestController
@RequestMapping("/download")
public class DownloadController {

	private boolean flag = false;
	
	// 下载文件的地址
	@Value("${filePath}")
	private String filePath;
	
	@Autowired
	private DownloadService downloadService;
	
	@GetMapping("/down")
	public void download(String username, HttpServletResponse response) throws Exception {
		if(filePath==null) {
			throw new Exception("文件目录不存在！");
		}
		try {
			flag = downloadService.downLoad(filePath+"acct.sql", response, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/downCheck")
	public Map<String, Object> downloadCheck(@RequestParam("username") String username) {
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(flag);
		if(flag) {
			result.put("message", "下载成功！");
		}else {
			result.put("message", "下载失败！");
		}
		return result;
	}

}
