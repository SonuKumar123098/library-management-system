package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.modal.Author;
import com.example.librarymanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody Author author){
        String response=authorService.addAuthor(author);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    // update the email id of an author  -->> observer lastActivity column
    @PutMapping("/update-email/{id}")
    public ResponseEntity updateEmail(@PathVariable("id") int id, @RequestParam("email") String email){
        String response =authorService.updateEmail(id,email);
        return new ResponseEntity(response,HttpStatus.FOUND);
    }
    // Give me the names of all the books written by a partiular author
//     @GetMapping("/all-book")
//    public ResponseEntity getAllBookById(@RequestParam("id") int id){
//        List<String> response=authorService.getAllBookById(id);
//        if(res)
//     }
    // give me the names of authors who have written more than 'x' number of books
}
