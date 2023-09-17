package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.modal.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    public StudentResponse addStudent(StudentRequest studentRequest);
    public StudentResponse getStudent(int regNo);
    public ResponseEntity deleteStudent(int regNo);
    public StudentResponse updateAge(int regNo, int age);
    public List<StudentResponse> getAllStudent();
    public List<StudentResponse> getAllMaleStudent();
}
