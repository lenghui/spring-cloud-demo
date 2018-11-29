import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.CommonUtils;

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
		System.out.println(PurposeDef.COS.getProductOriginDesc());
	}
	
	public Map getMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purpose", PurposeDef.COS);
		return map;
	}
}
