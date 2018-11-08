import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class FileTest {
	
	private static String filePath = "E:\\workspace_mytest\\spring-cloud-demo\\eureka-client\\temp\\";
	
	
	private static final Logger log = LoggerFactory.getLogger(FileTest.class);

	
	public static void main(String[] args) throws IOException {
		File target = new File(filePath+"test.xlsx");
		log.info(target.getAbsolutePath());
//		testFile.createNewFile();
//		if(testFile.exists()) {
//			System.out.println(true);
//		}else {
//			System.out.println(false);
//		}
	}
}
