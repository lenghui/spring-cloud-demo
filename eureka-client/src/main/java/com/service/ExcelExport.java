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

/**
 * 生成excel表格，实现导出功能
 * 
 * @author Administrator
 *
 */
@Service
public class ExcelExport {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelExport.class);

	@Autowired
	private StudentRepository repository;
	
	/**
	 * 生成excel表格，实现导出功能（目前生成的是xlsx文件类型，针对的是2007版本）
	 * @param response
	 * @throws IOException
	 */
	public void excelExport(HttpServletResponse response) throws IOException {
		// 创建一个XSSFWorkbook对象
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建sheet对象，并命名
		XSSFSheet sheet = workbook.createSheet("学生信息表");
		// 创建标题栏
		XSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("编号");
		titleRow.createCell(1).setCellValue("姓名");
		titleRow.createCell(2).setCellValue("年龄");
		titleRow.createCell(3).setCellValue("性别");
		// 获取操作数据（实现具体业务逻辑时，应该以参数的形式传入，实现解耦，能复用）
		List<Student> students = repository.findAll();
		// 使用for循环对单元格赋值
		for(Student student: students) {
			int rowNum = sheet.getLastRowNum()+1;
			XSSFRow row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue(student.getId());
			row.createCell(1).setCellValue(student.getStuName());
			row.createCell(2).setCellValue(student.getAge());
			row.createCell(3).setCellValue(student.getSex());
		}
		
		// 获取输出流，关联目标对象
		OutputStream os = response.getOutputStream();
		// Clears any data that exists in the buffer as well as the status code and
        response.reset();
        // 设置头信息
        response.setHeader("Content-disposition", "attachment; filename=details.xlsx");
        // 设置文件类型
        response.setContentType("application/msexcel");
        // write out this document to an Outputstream.
        // os关联源对象
        workbook.write(os);
        os.close();
        log.info("下载完成！");

	}
}
