package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.modal.LibraryCard;
import com.example.librarymanagementsystem.modal.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {

        //request dto to ->> model
        Student student=new Student();
        student.setGender(studentRequest.getGender());
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());

        //give a libraryCard to student
        LibraryCard libraryCard=new LibraryCard();
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);
        //saved model to database
        Student student1=studentRepository.save(student);//this will save in both student and librarycard

        // model ->> response dto
        StudentResponse studentResponse=new StudentResponse();
        studentResponse.setEmail(student1.getEmail());
        studentResponse.setName(student1.getName());
        studentResponse.setMessage("student added succesfully");
        studentResponse.setIssuedLibraryCardNo(student1.getLibraryCard().getCardNo());

        return studentResponse;
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional=studentRepository.findById(regNo);
        if(studentOptional.isPresent()) {
            Student student=studentOptional.get();
            // model ->> response dto
            StudentResponse studentResponse=new StudentResponse();
            studentResponse.setEmail(student.getEmail());
            studentResponse.setName(student.getName());
            studentResponse.setMessage("student found success");
            studentResponse.setIssuedLibraryCardNo(student.getLibraryCard().getCardNo());

            return studentResponse;
        }
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
        if(studentRepository.findById(regNo).isPresent()) {
            Student student = studentRepository.getById(regNo);
            student.setAge(age);
            studentRepository.save(student);
            return student;
        }
        return null;
    }

    public List<String> getAllStudent() {
        List<Student> studentList=studentRepository.findAll();
        List<String >names=new ArrayList<>();
        for(Student student:studentList){
            names.add(student.getName());
        }
        return names;
    }

    public List<String> getAllMaleStudent() {
        List<Student> studentList =studentRepository.getByGender(Gender.MALE);
        List<String>names=new ArrayList<>();
        for(Student student:studentList){
            names.add(student.getName());
        }
        return names;

    }

}
