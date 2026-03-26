package com.example.demo.student;
import java.time.LocalDate;
import java.time.Period;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "student")
public class Student {
    @Id                         
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    
    private Long id;
    private String name;

    @Column(unique = true) //Even if code fails, DB blocks duplicates
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")//Tells Jackson to expect date in this format from JSON
    private LocalDate dob;

    @Transient //calculate outside the database
    private Integer age;

    public Student(){

    }

    public Student( 
        Long id,
        String name,
        String email,
        LocalDate dob
     ){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;

    }
        public Student( 
        String name,
        String email,
        LocalDate dob
     ){
        this.name = name;
        this.email = email;
        this.dob = dob;
}
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public LocalDate getDate(){
        return dob;
    }
    public void setDate(LocalDate dob){
        this.dob = dob;
    }
    public Integer getAge(){
        return Period.between(dob, LocalDate.now()).getYears();
    }
    public void setAge(Integer age){
        this.age = age;
    }
    @Override
    public String toString(){
        return "Student{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", dob=" + dob +
            ", age=" + age +
            '}';
        }
    }


