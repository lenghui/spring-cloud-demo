package com.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DownloadService extends Thread{
	
	
	private static final Logger logger = LoggerFactory.getLogger(DownloadService.class);

	
	public boolean downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return false;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
            response.sendError(200, "下载成功!");
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
        return true;
    }
	
	// 模拟从资源端下载文件
	public Map<String, Object> downloadFileFromServer(String fileType) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据放款文件的文件生成规则，从t-1（20181120）天时间命名的文件夹中下载文件
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		String formTime = sdf.format(time);
		File file = new File("");
		String filePath = file.getAbsolutePath();
		File sourceFile = new File(filePath+"/"+formTime+"/DP.HUB_HAIER_"+fileType+".del");
		System.out.println(sourceFile.getAbsolutePath());
		// 创建目标文件
		String targetFilePath = filePath+"/temp/"+formTime;
		File targetFile = new File(targetFilePath);
		if (!targetFile.exists()) {
			targetFile.mkdir();
		}
		FileInputStream is = null;
		FileOutputStream out = null;
		byte[] b = null;
		try {
			is = new FileInputStream(sourceFile);
			b = new byte[is.available()];
			out = new FileOutputStream(targetFilePath+"/DP.HUB_HAIER_"+fileType+".del");
			is.read(b);
			out.write(b);
			out.close();
		} catch (FileNotFoundException e) {
			logger.info("下载对账文件时，未找到目标文件！{}",e);
			e.printStackTrace();
			map.put("meassage","文件下载失败！");
			return map;
		} catch (IOException e) {
			logger.info("下载文件报错!{}",e);
			e.printStackTrace();
			map.put("meassage","文件下载失败！");
			return map;
		}
		
		map.put("meassage","文件下载成功！");
		return map;
	}
	
	public String formatFileName(String fileName) {
		String[] strings = fileName.split("_");
		// "."属于特殊字符，分割时需要转义
		String[] strings3 = strings[2].split("\\.");
		return strings3[0];
	}
}
