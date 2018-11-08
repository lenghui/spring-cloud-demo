package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 读取excel中的信息
 * 
 * @author Administrator
 *
 */
@Service
public class ExcelImport {
	
	
	private static final Logger log = LoggerFactory.getLogger(ExcelImport.class);

	@Value("${filePath}")
	private String filePath;

	/**
	 * 读取excel中的信息
	 * @param fileName 文件名
	 * @throws IOException
	 */
	public void readExcel(String fileName) throws IOException {
		// 首先获取目标文件
		File target = new File(filePath+fileName);
		log.info(target.getAbsolutePath());
		// 判断文件类型，xlsx是2007版本，需要XSSFWorkbook处理，xls是2003版本，需要HSSFWorkbook处理
		String fileType = target.getName().substring(target.getName().indexOf(".")+1);
		log.info(fileType);
		// 获取输入流，准备读操作
		InputStream is = new FileInputStream(target);
		
		if(fileType.equals("xlsx")) {
			// 处理xlsx类型文件
			// 读取输入流中的数据
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			// 获取第一个sheet页
			XSSFSheet sheet = workbook.getSheetAt(0);
			// for循环逐行读取
			for(Row row: sheet) {
				log.info("当前行数："+row.getRowNum());
				// 去除首行标题栏
				if(row.getRowNum()==0) {
					continue;
				}
				// 读取单元格中的信息（注意字段类型）
				int id = (int) row.getCell(0).getNumericCellValue();
				int age = (int) row.getCell(1).getNumericCellValue();
				String name = row.getCell(2).getStringCellValue();
				String sex = row.getCell(3).getStringCellValue();
				log.info("id:"+id+", 姓名:"+name+", 年龄:"+age+", 性别:"+sex);
			}
		}else if (fileType.equals("xls")) {
			// 处理xls文件
			// 操作同上
			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for(Row row: sheet) {
				// 去除首行标题栏
				if(row.getRowNum()==0) {
					continue;
				}
				log.info("当前行数："+row.getRowNum());
				int id = (int) row.getCell(0).getNumericCellValue();
				int age = (int) row.getCell(1).getNumericCellValue();
				String name = row.getCell(2).getStringCellValue();
				String sex = row.getCell(3).getStringCellValue();
				log.info("id:"+id+", 姓名:"+name+", 年龄:"+age+", 性别:"+sex);
			}
		}else {
			log.error("文件类型错误，无法处理！");
			
		}
		
		
		
	}
	
	
}
