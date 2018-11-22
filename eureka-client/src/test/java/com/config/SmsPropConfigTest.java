package com.config;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsPropConfigTest {

	@Autowired
	private SmsPropConfig sms;
	
	@Test
	public void test() {
		System.out.println("********************************");
		System.out.println(sms.getTemp());
		String name = "张三";
		Date date = new Date();
		int month = date.getMonth();
		int day = date.getDay();
		BigDecimal money = new BigDecimal(18.02);
		Object[] arr = {name, month, day, money};
		String message = MessageFormat.format(sms.getTemp(), arr);
		System.out.println(message);
		
	}

}
