package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.modal.Transaction;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/book-id/{bookId}/student-id/{studentId}")
    public ResponseEntity issueBook(@PathVariable("bookId") int bookId, @PathVariable("studentId") int studentId){
        try{
            IssueBookResponse issueBookResponse= transactionService.issueBook(bookId,studentId);
            return new ResponseEntity(issueBookResponse, HttpStatus.ACCEPTED);
        }catch (RuntimeException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/book-id/{bookId}")
    public ResponseEntity returnBook(@PathVariable("bookId") int bookId){
        String response=transactionService.returnBook(bookId);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
