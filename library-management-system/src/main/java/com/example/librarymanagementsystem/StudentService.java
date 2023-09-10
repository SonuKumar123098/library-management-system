package com.example.librarymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public ResponseEntity deleteStudent(int regNo) {
        if(getStudent(regNo)!=null){
             studentRepository.deleteById(regNo);
             return new ResponseEntity<>("student deleted successfully",HttpStatus.FOUND);
        }
        return new ResponseEntity("invalid id", HttpStatus.NOT_FOUND);
    }

    public Student updateAge(int regNo, int age) {
        if(getStudent(regNo)!=null) {
            Student student = studentRepository.getById(regNo);
            student.setAge(age);
            addStudent(new Student(student.getRegNo(),student.getGender(),student.getName(),student.getEmail(),student.getAge()));
            return student;
        }
        return null;
    }

    public List<Student> getAllStudent() {
        List<Student> studentList=studentRepository.findAll();
        return studentList;
    }

    public List<Student> getAllMaleStudent() {
        List<Student> studentList =getAllStudent();
        List<Student>ans=new ArrayList<>();
        for(Student student:studentList){
            if(student.getGender().equals(Gender.MALE)){
                ans.add(student);
            }
        }
        return ans;

    }
}
