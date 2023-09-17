package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.modal.Author;

public interface AuthorService {
    public String updateEmail(int id, String email);

    public String addAuthor(Author author);
}
