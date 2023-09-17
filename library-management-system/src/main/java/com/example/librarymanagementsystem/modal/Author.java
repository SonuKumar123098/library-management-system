package com.example.librarymanagementsystem.modal;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    int age;
    @Column(unique = true, nullable = false)
    String email;
    @UpdateTimestamp
    Date lastActivity;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book>books=new ArrayList<>();
}
