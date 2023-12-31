package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.exception.AuthorNotFoundException;
import com.example.librarymanagementsystem.modal.Author;
import com.example.librarymanagementsystem.modal.Book;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.transformer.BookTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    public String addBook(Book book) {
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if (!authorOptional.isPresent()) {
            throw new AuthorNotFoundException("invalid authorId!");
        }
        Author author = authorOptional.get();
        author.getBooks().add(book);
        book.setAuthor(author);
        book.setIssued(false);
        Author savedAuthor = authorRepository.save(author);
        return "book added successfully!";
    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThan(double cost, String genre) {
        List<Book> books=bookRepository.getBooksByGenreAndCostGreaterThan(genre,cost);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book:books){
            BookResponse bookResponse=BookTransformer.BookToBookResponse(book);
            bookResponses.add(bookResponse);
        }
        return  bookResponses;
    }

    public List<BookResponse> getBooksByGenreAndCostGreaterThanByHQL(double cost, Genre genre) {
        List<Book> books=bookRepository.getBooksByGenreAndCostGreaterThanByHQL(genre,cost);
        List<BookResponse> bookResponses=new ArrayList<>();
        for(Book book:books){
            bookResponses.add(BookTransformer.BookToBookResponse(book));
        }
        return  bookResponses;
    }
    public String deleteBook(int id) {
        Optional<Book> bookOptional=bookRepository.findById(id);
        if(bookOptional.isPresent()){
            bookRepository.deleteById(id);
        }
        return " book deleted successfully";
    }

    public List<BookResponse> getAllBookByGenre(String genre) {
        List<Book>books=bookRepository.getAllBookByGenre(genre);
        List<BookResponse>bookResponses=new ArrayList<>();
        for (Book book:books){
            bookResponses.add(BookTransformer.BookToBookResponse((book)));
        }
        return bookResponses;
    }


}
