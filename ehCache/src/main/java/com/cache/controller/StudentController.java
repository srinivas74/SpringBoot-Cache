package com.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
	
	
	
	/**
	 * @Cacheable are not executed again if a value already exists in the cache for the cache key. 
	 * If the value does not exist in the cache, then the method is executed and places its value in the cache.
	 * 
	 */
	
	@Cacheable(value = "studentCache",key = "#studentName")
	@GetMapping("/student/{studentName}")
	public Student getStudent(@PathVariable("studentName") String studentName) {
		return studentService.getStudent(studentName);
	}
	
	
	/**
	 * The methods always executes and updates cache
	 */
	@CachePut(cacheNames = "studentCache", key = "#student.userName")
	@PutMapping("/student")
	public Student updateOrSaveStudent(@RequestBody Student student) {
		return studentService.updateOrSaveStudent(student);
	}
	
	
	/**
	 * 
	 * given expiry as 1 min in xml configuration
	 * to empty the cache based on requests we can use this method
	 */
	@CacheEvict(cacheNames = {"studentCache"}, key = "#studentName")
	@DeleteMapping("/student/{studentName}")
	public String deleteStudent(@PathVariable("studentName") String studentName) {
		return studentService.deleteStudent(studentName);
	}
	

}
