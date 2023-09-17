package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.modal.Book;

import java.util.List;

public interface BookService {
    public String addBook(Book book);
    public List<BookResponse> getBooksByGenreAndCostGreaterThan(double cost, String genre);
    public List<BookResponse> getBooksByGenreAndCostGreaterThanByHQL(double cost, Genre genre);
    public String deleteBook(int id);
    public List<BookResponse> getAllBookByGenre(String genre);
}
