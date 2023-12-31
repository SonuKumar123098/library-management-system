package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Genre;
import com.example.librarymanagementsystem.modal.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    //writing sql query
    @Query(value = "select * from book_info where genre= :genre and cost > :cost",nativeQuery = true)
    List<Book> getBooksByGenreAndCostGreaterThan(String genre,double cost);

    @Query(value = " select * from book_info where genre= : genre", nativeQuery = true)
    List<Book> getAllBookByGenre(String genre);

    //writing hql query
    @Query(value = " select b from Book b where genre =:genre and cost >:cost")
    List<Book> getBooksByGenreAndCostGreaterThanByHQL(Genre genre, double cost);
}
