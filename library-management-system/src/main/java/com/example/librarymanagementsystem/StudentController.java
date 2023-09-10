package com.example.librarymanagementsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@RequestParam("id") int regNo){
        return studentService.deleteStudent(regNo);
    }

    // update the age of a student  ---> regNo, age
    @PutMapping("/update-age/{age}")
    public ResponseEntity updateAge(@RequestParam("id") int regNo, @PathVariable("age") int age){
        Student student=studentService.updateAge(regNo,age);
        if(student!=null) return new ResponseEntity(student,HttpStatus.FOUND);
        return new ResponseEntity("invalid id",HttpStatus.NOT_FOUND);
    }
    // get all the students in the db
    @GetMapping("/all-student")
    public ResponseEntity getAllStudent(){
        List<Student> studentList=studentService.getAllStudent();
        if(studentList.size()!=0)
            return new ResponseEntity<>(studentList,HttpStatus.FOUND);
        return new ResponseEntity<>("No student found in database",HttpStatus.NOT_FOUND);
    }
    // get list of all male students
    @GetMapping("/all-male-student")
    public ResponseEntity getAllMaleStudent(){
        List<Student> studentList=studentService.getAllMaleStudent();
        if(studentList.size()!=0)
            return new ResponseEntity<>(studentList,HttpStatus.FOUND);
        return
                new ResponseEntity<>("No male student found",HttpStatus.NOT_FOUND);
    }
}
