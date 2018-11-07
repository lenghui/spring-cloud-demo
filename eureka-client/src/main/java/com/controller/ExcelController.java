package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.ExcelExport;
import com.service.ExcelImport;

/**
 * excel 导入导出功能调用
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

	@Autowired
	private ExcelImport excelImport;
	
	@Autowired
	private ExcelExport excelExport;
	
	
	@GetMapping("/read")
	public String readExcel() throws IOException {
		excelImport.readExcel();
		return "excute";
	}
	
	@GetMapping("/down")
	public String downExcel(HttpServletResponse response) throws IOException {
		excelExport.excelExport(response);
		return "excute";
	}
	
}
