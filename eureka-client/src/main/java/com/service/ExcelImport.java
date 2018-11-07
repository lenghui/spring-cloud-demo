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
import org.springframework.stereotype.Service;

@Service
public class ExcelImport {
	
	
	private static final Logger log = LoggerFactory.getLogger(ExcelImport.class);


	public void readExcel() throws IOException {
		File target = new File("static/test.xlsx");
		log.info(target.getAbsolutePath());
		String fileType = target.getName().substring(target.getName().indexOf(".")+1);
		log.info(fileType);
		InputStream is = new FileInputStream(target);
		
		if(fileType.equals("xlsx")) {
			// 处理xlsx类型文件
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
			for(Row row: sheet) {
				log.info("当前行数："+row.getRowNum());
				// 去除首行标题栏
				if(row.getRowNum()==0) {
					continue;
				}
				int id = (int) row.getCell(0).getNumericCellValue();
				int age = (int) row.getCell(1).getNumericCellValue();
				String name = row.getCell(2).getStringCellValue();
				String sex = row.getCell(3).getStringCellValue();
				log.info("id:"+id+", 姓名:"+name+", 年龄:"+age+", 性别:"+sex);
			}
		}else if (fileType.equals("xls")) {
			// 处理xls文件
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
