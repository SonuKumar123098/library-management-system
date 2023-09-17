package com.example.librarymanagementsystem.modal;

import com.example.librarymanagementsystem.Enum.Genre;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book_info")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, unique = true)
    String title;
    @Enumerated(EnumType.STRING)
    Genre genre;
    int numberOfPage;
    double cost;
    boolean issued;
    @CreationTimestamp
    Date publishedDate;
    @ManyToOne
    @JoinColumn
    Author author;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    List<Transaction> transactionList=new ArrayList<>();

}
