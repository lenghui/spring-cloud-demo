package com.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DownloadServiceTest {

	@Autowired
	private DownloadService download;
	
	@Test
	public void test() {
		download.downloadFileFromServer("RUT");
	}

}
