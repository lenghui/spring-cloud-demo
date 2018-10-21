package com.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

 /**
  * 
  * 上传文件
 * @author Administrator
 *
 */
@Service
public class UploadFile {

	@SuppressWarnings("finally")
	public Map<String, Object> upload(MultipartFile file) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 判空
		if(file.isEmpty()) {
			result.put("message", "请选择上传文件！");
			return result ;
		}
		// 对文件做处理
		File tempFile = new File("temp");
		tempFile.mkdir();
		System.out.println(tempFile.getAbsolutePath());
		try {
			file.transferTo(new File(tempFile.getAbsoluteFile()+"\\"+file.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			result.put("message", "文件上传成功！");
			return result;
		}
	}
	
}
