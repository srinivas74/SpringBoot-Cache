package com.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cache.model.Student;
import com.cache.service.StudentService;

@RestController
public class StudentController {
	
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private JCacheCacheManager cacheManager;
	

	

	@GetMapping("/student/{studentName}")
	public Student getStudent(@PathVariable("studentName") String studentName) {
		Cache cache= cacheManager.getCache("studentCache");
		if(cache.get(studentName)!=null) {
			Student student=(Student)cache.get(studentName).get();
			if(student!=null) {
				return student;
			}
		}
		Student student= studentService.getStudent(studentName);
		cache.put(studentName, student);
		return student;
		
	}
	
	
	@PutMapping("/student")
	public Student updateOrSaveStudent(@RequestBody Student student) {
		Cache cache= cacheManager.getCache("studentCache");
		Student studentModel=studentService.updateOrSaveStudent(student);
		cache.put(student.getUserName(), studentModel);
		return studentModel;
		
	}
	
	

	@DeleteMapping("/student/{studentName}")
	public String deleteStudent(@PathVariable("studentName") String studentName) {
		Cache cache= cacheManager.getCache("studentCache");
		cache.evict(studentName);
		return studentService.deleteStudent(studentName);
	}
	

}
