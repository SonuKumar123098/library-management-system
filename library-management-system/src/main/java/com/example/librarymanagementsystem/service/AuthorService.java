package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.modal.Author;
import com.example.librarymanagementsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        Author author1=authorRepository.save(author);
        return "author added successfully";
    }

    public String updateEmail(int id, String email) {
        Optional<Author> authorOptional=authorRepository.findById(id);
        if(authorOptional.isPresent()){
            Author author=authorOptional.get();
            author.setEmail(email);
            Author savedAuthor=authorRepository.save(author);
            return  "email updated successfully!";
        }
        return "invalid authorID!";
    }
}
