package com.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cache.model.Student;
import com.cache.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	

	public Student getStudent(String studentName) {
		try {
			return studentRepository.findByUserName(studentName);
		}
		catch(Exception e) {
			throw new NullPointerException("STUDENT NOT FOUND");
		}		
	}
	

	public Student updateOrSaveStudent(Student student) {
		try {

			return studentRepository.save(student);
			}
			
		catch(Exception e) {
			throw new NullPointerException("STUDENT NOT FOUND");
		}		
	}
	
	
	public String deleteStudent(String studentName) {
		try {
			studentRepository.deleteById(studentRepository.findByUserName(studentName).getId());
		}
		catch(Exception e) {
			throw new NullPointerException("USER NOT FOUND");
		}		
		return "Student   "+studentName + "   Deleted";
	}

}
