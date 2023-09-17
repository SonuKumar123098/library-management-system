package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.dto.requestDTO.StudentRequest;
import com.example.librarymanagementsystem.dto.responseDTO.StudentResponse;
import com.example.librarymanagementsystem.service.StudentService;
import com.example.librarymanagementsystem.modal.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse response=studentService.addStudent(studentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        StudentResponse response=studentService.getStudent(regNo);
        if(response!=null) return new ResponseEntity(response,HttpStatus.FOUND);
        return new ResponseEntity("invalid id", HttpStatus.BAD_GATEWAY);
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo){
        return studentService.deleteStudent(regNo);
    }

    // update the age of a student  ---> regNo, age
    @PutMapping("/update-age/{age}")
    public ResponseEntity updateAge(@RequestParam("id") int regNo, @PathVariable("age") int age){
        Student student=studentService.updateAge(regNo,age);
        if(student!=null) return new ResponseEntity("Age updated successfully",HttpStatus.FOUND);
        return new ResponseEntity("invalid id",HttpStatus.NOT_FOUND);
    }
    // get all the students in the db
    @GetMapping("/all-student")
    public ResponseEntity getAllStudent(){
        List<String> studentList=studentService.getAllStudent();
        if(studentList.size()!=0)
            return new ResponseEntity<>(studentList,HttpStatus.FOUND);
        return new ResponseEntity<>("No student found in database",HttpStatus.NOT_FOUND);
    }
    // get list of all male students
    @GetMapping("/all-male-student")
    public ResponseEntity getAllMaleStudent(){
        List<String> studentList=studentService.getAllMaleStudent();
        if(studentList.size()!=0)
            return new ResponseEntity<>(studentList,HttpStatus.FOUND);
        return
                new ResponseEntity<>("No male student found",HttpStatus.NOT_FOUND);
    }
}
