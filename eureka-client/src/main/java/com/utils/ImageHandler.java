package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * 将图片转为string
 * 将string转为图片
 * 
 * @author ASUS
 *
 */
public class ImageHandler {
	
	public String imageToString() throws IOException {
		File file = new File("");
		String filePath = file.getAbsolutePath();
		File imageFile = new File(filePath+"/static/1.png");
		System.out.println(imageFile.exists());
		InputStream inputStream = new FileInputStream(imageFile);
		// 创建一个与inputstrean流大小相同的byte数组
		byte[] data = new byte[inputStream.available()];
		// 将inputstream读入data数组
		inputStream.read(data);
		inputStream.close();
		// 加密
		String d = Base64.getEncoder().encodeToString(data);
		return d;
	}
	
	public boolean StringToImage(String imageString, String fileName) throws IOException {
		
		if(imageString==null) {
			return false;
		}
		
		File file = new File("");
		String filePath = file.getAbsolutePath();
		byte[] b = Base64.getDecoder().decode(imageString);
		// 处理数据
	     for(int i = 0; i < b.length; ++i) {
	         if (b[i] < 0) {
	             b[i] += 256;
	         }
	     }
		OutputStream outputStream = new FileOutputStream(filePath+"/static/2.png");
		outputStream.write(b);
		outputStream.flush();
		outputStream.close();
	     
		
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		ImageHandler imageHandler = new ImageHandler();
		imageHandler.StringToImage(imageHandler.imageToString(), "");
		System.out.println(imageHandler.imageToString());
	}

}
