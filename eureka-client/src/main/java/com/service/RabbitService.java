package com.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {
	
	/*@RabbitListener(queues="lh-test")
	public void queue() {
		System.out.println("12312");
	}*/
}
