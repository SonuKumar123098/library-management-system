package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.modal.Student;

public class StudentTransformer {
    public static Student StudentRequestToStudent(StudentRequest studentRequest){
        return Student.builder()
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .name(studentRequest.getName())
                .build();
    }
    public static StudentResponse StudentToStudentResponse(Student student){
        return StudentResponse.builder()
                .email(student.getEmail())
                .issuedLibraryCardNo(student.getLibraryCard().getCardNo())
                .name(student.getName())
                .age(student.getAge())
                .build();
    }
}
