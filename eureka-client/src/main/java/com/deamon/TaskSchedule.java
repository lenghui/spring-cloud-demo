package com.deamon;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.service.DownloadService;

@EnableScheduling
@Component
public class TaskSchedule {
	
	
	private static final Logger logger = LoggerFactory.getLogger(TaskSchedule.class);

	@Autowired
	private DownloadService downloadService;
	
	@Scheduled(fixedDelay=3000)
	public void name() {
		System.out.println("time:"+new Date());
	}
	
	@Scheduled(cron="0 * * * * ?")
	public void cronName() {
		logger.info("开始下载对账文件！");
		downloadService.downloadFileFromServer("LOAN");
		downloadService.downloadFileFromServer("RUT");
		downloadService.downloadFileFromServer("SHD");
		
	}
}
