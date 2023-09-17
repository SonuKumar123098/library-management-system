package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.modal.Book;
import com.example.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book){
        try{
            String response = bookService.addBook(book);
            return new ResponseEntity(response,HttpStatus.CREATED);

        }catch (AuthorNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // delete a book
    @DeleteMapping("/delete")
    public ResponseEntity deleteBook(@RequestParam("bId") int id){
        String response=bookService.deleteBook(id);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    // give me names of all the books of a particular genre
    @GetMapping("/get-by-genre")
    public ResponseEntity getAllBookByGenre(@RequestParam("genre") String genre){
        List<BookResponse> bookResponses=bookService.getAllBookByGenre(genre);
        return new ResponseEntity<>(bookResponses,HttpStatus.FOUND);
    }

    // give me names of all the books of a particular genre and cost gretaer than x rs
    @GetMapping("/get-by-genre-cost")//sql->structured query language
    public ResponseEntity getBooksByGenreAndCostGreaterThan(@RequestParam("genre") String genre, @RequestParam("cost") double cost){
        List<BookResponse> bookResponses=bookService.getBooksByGenreAndCostGreaterThan(cost,genre);
        return new ResponseEntity(bookResponses,HttpStatus.FOUND);
    }
    // hql -> hibernate query language
    @GetMapping("/get-by-genre-cost-hql")
    public ResponseEntity getBooksByGenreAndCostGreaterThanByHQL(@RequestParam("genre") Genre genre, @RequestParam("cost") double cost){
        List<BookResponse> bookResponses=bookService.getBooksByGenreAndCostGreaterThanByHQL(cost,genre);
        return new ResponseEntity(bookResponses,HttpStatus.FOUND);
    }
    // give me all the books having number of pages between 'a' and 'b'

    // give me the names of all the authors who write a particular genre
}
