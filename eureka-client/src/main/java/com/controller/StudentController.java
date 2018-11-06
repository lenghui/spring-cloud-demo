package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.StudentRepository;
import com.model.Student;

@RestController
@RequestMapping("/student")
@CacheConfig(cacheNames="student")
public class StudentController {

	@Autowired
	private StudentRepository studentRep;
	
	// value指定缓存的名字（不是key值）,keyGenerator在RedisConfig类中自定义
	@GetMapping("/find")
	@Cacheable(value="findAllStudent", keyGenerator = "studentKeyGenerator")
	public String find() {
		List<Student> students = studentRep.findAll();
		return students.toString();
	}
	
	// 删除缓存
	// 使用value="findAllStudent"指定要清除的redis缓存
	@GetMapping("/del")
	@CacheEvict(value="findAllStudent", allEntries=true)
	public String delCache() {
		return "清空findAllStudent对应key值的缓存";
	}
	
}
