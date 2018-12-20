package com;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {

	
	private static final Logger logger = LoggerFactory.getLogger(TimerTask.class);

	
	@Scheduled(fixedDelay=100000)
	public void createFile() {
		logger.info("开始创建文件夹");
		File fileD = new File("/ceshi");
		if (!fileD.exists()) {
			fileD.mkdir();
		}
		System.out.println(fileD.getAbsolutePath());
	}
}
