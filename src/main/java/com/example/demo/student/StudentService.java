package com.example.demo.student;

// import java.time.LocalDate;
// import java.time.Month;
import java.util.List;

import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.GetMapping;

@Service
public class StudentService {
	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents(){
		return studentRepository.findAll();	
	}

	public void addStudent(Student student){
		studentRepository.save(student);
	}
}
