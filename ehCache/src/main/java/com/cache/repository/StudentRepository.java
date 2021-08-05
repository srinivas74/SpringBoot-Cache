package com.cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cache.model.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer>{
	
	Student findByUserName(String userName);

}
