package com.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mysql.entity.PersonMapper;

@Service
public class PersonService implements ApplicationRunner{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public String find() {
		List person  = jdbcTemplate.query("select * from person", new PersonMapper());
//		List person = jdbcTemplate.query("select * from person",  new BeanPropertyRowMapper(Person.class));
		System.out.println(person.toString());
		return person.toString();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("---------------------启动完成执行find方法-----------------------------");
		find();
		
	}
}
