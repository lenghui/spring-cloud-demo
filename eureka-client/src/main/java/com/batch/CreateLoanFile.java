package com.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * 模拟跑批，生成对账文件
 * 放款交易文件
 * 
 * @author Administrator
 * 描述：LOAN: 放款报表（20180906日凌晨跑批，跑完批量，核心营业日期为20180906，数据生成是20180905，文件会出现在20180905文件夹中）
 */
@Service
public class CreateLoanFile {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CreateLoanFile.class);

	
	public Map<String, Object> loanFile(){
		
		Map<String, Object> resultmMap = new HashMap<String, Object>();
		
		// 根据日期生成文件夹，格式20181130
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		String formTime = sdf.format(time);
		File rootDir = new File("");
		String filePath = rootDir.getAbsolutePath();
		File creatFile = new File(filePath+"/"+formTime);
		if (!creatFile.exists()) {
			creatFile.mkdir();
		}
		
		// 数据业务生成
		File createLoanFile = new File(filePath+"/"+formTime+"/"+"DP.HUB_HAIER_LOAN.del");
		FileOutputStream fos = null;
		String loanString = "放款对账文件";
		byte[] b = loanString.getBytes();
		try {
			fos = new FileOutputStream(createLoanFile);
			fos.write(b);
			fos.close();
		} catch (FileNotFoundException e) {
			logger.info("生成放款对账文件报错:{}",e);
			e.printStackTrace();
			resultmMap.put("message", "放款文件生成失败！");
			return resultmMap;
		} catch (IOException e) {
			logger.info("向贷款文件中写内容时报错：{}",e);
			e.printStackTrace();
			resultmMap.put("message", "放款文件生成失败！");
			return resultmMap;
		}
		
		resultmMap.put("message", "放款文件生成！");
		return resultmMap;
	}
}
