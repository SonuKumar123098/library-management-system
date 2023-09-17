package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.dto.responseDTO.BookResponse;
import com.example.librarymanagementsystem.modal.Book;

public class BookTransformer {

    public static BookResponse BookToBookResponse(Book book){
        return BookResponse.builder()
                .authorName(book.getAuthor().getName())
                .title(book.getTitle())
                .cost(book.getCost())
                .genre(book.getGenre())
                .numberOfPages(book.getNumberOfPage())
                .build();
    }
}
