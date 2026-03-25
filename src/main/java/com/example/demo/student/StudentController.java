package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping(path = "api/v1/student")

public class StudentController {

private final StudentService studentService;//Object
//Constructor injection / Dependency injection
@Autowired
public StudentController(StudentService studentService){
this.studentService = studentService;
}

@GetMapping
public List<Student> getStudents(){
    return studentService.getStudents();
}

@PostMapping
public void registerStudent(@RequestBody Student student){
    studentService.addStudent(student); //call service
}

@DeleteMapping("{id}")
public void deleteStudent(@PathVariable Long id){
    studentService.deleteStudent(id);
}

}
