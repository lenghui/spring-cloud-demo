import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.CommonUtils;

import javassist.expr.NewArray;

public class FileTest {
	
	private static String filePath = "E:\\workspace_mytest\\spring-cloud-demo\\eureka-client\\temp\\";
	
	
	private static final Logger log = LoggerFactory.getLogger(FileTest.class);

	
	public static void main(String[] args) throws Exception {
//		File target = new File(filePath+"test.xlsx");
//		log.info(target.getAbsolutePath());
//		testFile.createNewFile();
//		if(testFile.exists()) {
//			System.out.println(true);
//		}else {
//			System.out.println(false);
//		}
		/*Map<String, Object> headMap = new HashMap<String, Object>();
		headMap.put("applNo", "123");
		headMap.put("applSeq", "10001");
		HaiercashPayApplyBean reqBodyBean = new HaiercashPayApplyBean();
		reqBodyBean.setApplyNo("123");
		reqBodyBean.setChannelNo("B2");
		reqBodyBean.setTradeCode("123");
		reqBodyBean.setData(JSONObject.toJSONString(headMap));
		String string = new ObjectMapper().writeValueAsString(reqBodyBean);
		System.out.println(string);*/
		/*Map<String, Object> bodyMap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ceshi", "ceshi");
		bodyMap.put("data", data);
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("head", headMap);
		requestMap.put("body", bodyMap);
		System.out.println(JSONObject.toJSONString(requestMap));*/
//		String mapString = "{\"head\":{\"retFlag\":\"L0099\",\"retMsg\":\"网络通讯异常\"}}";
		FileTest fileTest = new FileTest();
//		String str = PurposeDef.valueOf("COS").toString();
//		System.out.println(str);
//		System.out.println(PurposeDef.COS.getProductOriginDesc());
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("uuid", UUID.randomUUID());
//		
//		System.out.println(map.get("uuid").toString().replaceAll("-", ""));
/*		File file = new File("");
		String filePath = file.getAbsolutePath();
		File sourceFile = new File(filePath+"/temp/20181130.DP.HUB_HAIER_LOAN.del");
		FileInputStream is = new FileInputStream(sourceFile);
		FileOutputStream out = new FileOutputStream("20181130.loan.del");
		byte[] b = new byte[is.available()];
		System.out.println(b.length);
		is.read(b);
		out.write(b);
		out.close();
		*/
		/*Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(date));
		log.info("message:{}",date);*/
		String fileName = "DP.HUB_HAIER_LOAN.del";
		System.out.println(fileTest.formatFileName(fileName));
	}
	
	public Map getMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purpose", PurposeDef.COS);
		return map;
	}
	
	public String formatFileName(String fileName) {
		String[] strings = fileName.split("_");
		// "."属于特殊字符，分割时需要转义
		String[] strings3 = strings[2].split("\\.");
		return strings3[0];
	}
}
