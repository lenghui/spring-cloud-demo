package com.controller.upload;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.UploadFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	private UploadFile uploadFile;
	
	@PostMapping("/up")
	public Map<String, Object> upload(@RequestParam("file") MultipartFile file){
		return uploadFile.upload(file);
	}
	
}
