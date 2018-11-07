package com.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inter.StudentRepository;
import com.model.Student;

@Service
public class ExcelExport {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelExport.class);

	@Autowired
	private StudentRepository repository;
	
	public void excelExport(HttpServletResponse response) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("学生信息表");
		//创建标题栏
		XSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("编号");
		titleRow.createCell(1).setCellValue("姓名");
		titleRow.createCell(2).setCellValue("年龄");
		titleRow.createCell(3).setCellValue("性别");
		
		List<Student> students = repository.findAll();
		for(Student student: students) {
			int rowNum = sheet.getLastRowNum()+1;
			XSSFRow row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(student.getId());
			row.createCell(1).setCellValue(student.getStuName());
			row.createCell(2).setCellValue(student.getAge());
			row.createCell(3).setCellValue(student.getSex());
		}
		
		// 输出Excel文件
		OutputStream os = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=details.xlsx");
        response.setContentType("application/msexcel");
        workbook.write(os);
        os.close();
        log.info("下载完成！");

	}
}
