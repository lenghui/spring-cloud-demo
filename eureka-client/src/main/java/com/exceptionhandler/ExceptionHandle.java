package com.exceptionhandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;


/**
 * 
 * 用于捕获全局变量
 * 
 * @author Administrator
 *
 */
@RestControllerAdvice
public class ExceptionHandle {
	
	
	@Value("${spring.http.multipart.max-file-size:1MB}")
	String size;
	
	// 捕获文件大小超出限制异常
	@ExceptionHandler(value= MultipartException.class)
	public Map<String, Object> fileMaxSizeException(MultipartException exception){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("CODE", "500");
		result.put("message", "文件大小超过"+size+"!");
		return result;
	}
}
