package com.example.librarymanagementsystem.exception;

import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message){
        super(message);
    }
}
