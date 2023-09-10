package com.example.librarymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody Student student){
        Student student1=studentService.addStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        Student student=studentService.getStudent(regNo);
        if(student==null)
            return new ResponseEntity<>("invalid id",HttpStatus.BAD_GATEWAY);
        return new ResponseEntity(student,HttpStatus.FOUND);
    }
    // delete a student --> regNo

    // update the age of a student  ---> regNo, age

    // get all the students in the db

    // get list of all male students
}
