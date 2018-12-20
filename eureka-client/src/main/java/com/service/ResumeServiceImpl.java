package com.service;

import org.springframework.stereotype.Service;

import com.inter.ResumeServiceInter;

@Service
public class ResumeServiceImpl implements ResumeServiceInter{

	@Override
	public void storeResume() {
		 System.out.println("任务已经执行.....................................");		
	}
	
}
