package com.example.librarymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student addStudent(Student student) {
        Student student1=studentRepository.save(student);
        return student1;
    }

    public Student getStudent(int regNo) {
        Optional<Student> student=studentRepository.findById(regNo);
        if(student.isPresent())
            return student.get();
        return null;
    }
}
