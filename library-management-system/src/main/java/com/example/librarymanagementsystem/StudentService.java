package com.example.librarymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student addStudent(Student student) {
        Student student1=studentRepository.save(student);
        return student1;
    }
}
