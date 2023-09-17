package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.modal.LibraryCard;
import com.example.librarymanagementsystem.modal.Student;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.service.StudentService;
import com.example.librarymanagementsystem.transformer.LibraryCardTransformer;
import com.example.librarymanagementsystem.transformer.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {

        //request dto to ->> model
        Student student= StudentTransformer.StudentRequestToStudent(studentRequest);
        //give a libraryCard to student
        LibraryCard libraryCard= LibraryCardTransformer.CreateLibraryCard();
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);
        //saved model to database
        Student student1=studentRepository.save(student);//this will save in both student and librarycard

        // model ->> response dto
        return StudentTransformer.StudentToStudentResponse(student1);
    }

    public StudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional=studentRepository.findById(regNo);
        if(studentOptional.isPresent()) {
            Student student=studentOptional.get();
            // model ->> response dto
            return StudentTransformer.StudentToStudentResponse(student);
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

    public StudentResponse updateAge(int regNo, int age) {
        if(studentRepository.findById(regNo).isPresent()) {
            Student student = studentRepository.getById(regNo);
            student.setAge(age);
            Student savedStudent=studentRepository.save(student);
            return StudentTransformer.StudentToStudentResponse(savedStudent);
        }
        return null;
    }

    public List<StudentResponse> getAllStudent() {
        List<Student> studentList=studentRepository.findAll();
        List<StudentResponse >studentResponses=new ArrayList<>();
        for(Student student:studentList){
            studentResponses.add(StudentTransformer.StudentToStudentResponse(student));
        }
        return studentResponses;
    }

    public List<StudentResponse> getAllMaleStudent() {
        List<Student> studentList =studentRepository.getByGender(Gender.MALE);
        List<StudentResponse>studentResponses =new ArrayList<>();
        for(Student student:studentList){
            studentResponses.add(StudentTransformer.StudentToStudentResponse(student));
        }
        return studentResponses;

    }

}
