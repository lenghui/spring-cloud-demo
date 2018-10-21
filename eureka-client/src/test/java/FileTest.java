import java.io.File;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
		File testFile = new File("testFile.txt");
		System.out.println(testFile.getAbsolutePath());
		testFile.createNewFile();
		if(testFile.exists()) {
			System.out.println(true);
		}else {
			System.out.println(false);
		}
	}
}
