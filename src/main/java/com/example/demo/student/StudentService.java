package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Service
public class StudentService {
	private final StudentRepository studentRepository;
	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents(){
		return studentRepository.findAll();	
	}

	@Transactional
	public void addStudent(Student student){
		Optional<Student> studentOptional = 
		studentRepository.findStudentByEmail(student.getEmail().trim());
		
		if(studentOptional.isPresent()){ 
			//Triggers rollback
			throw new IllegalStateException("Email "+student.getEmail()+" is already taken");	
		}
		studentRepository.save(student);//Committed if no exception
	}

	@Transactional
	public void updateStudent(
		Long id,
		String name,
		String email){
	Student student = studentRepository.findById(id)
	.orElseThrow(()-> new IllegalStateException(
		"student with id" + id + "does not exist"));
		
		if(name != null &&
			name.length() > 0 &&
			 !Objects.equals(student.getName(), name)){
			student.setName(name);
		}
		
		if(email != null &&
			email.length() > 0 &&
			 !Objects.equals(student.getEmail(), email)){
			Optional<Student> studentOptional = studentRepository
			.findStudentByEmail(email);

		if(studentOptional.isPresent()){
		throw new IllegalStateException("email taken");
		}
		} 
		student.setEmail(email);
	}
	 
	public void deleteStudent(Long id){
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException(
				"student with id" + id + " does not exist"
			);
			}
		studentRepository.deleteById(id);
		
}
}
