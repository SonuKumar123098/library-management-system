package com.example.librarymanagementsystem.modal;

import com.example.librarymanagementsystem.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LibraryCard {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String cardNo;
    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;
    @CreationTimestamp
    Date issueDate;
    @OneToOne
    @JoinColumn(name="email")
    Student student;
    @OneToMany(mappedBy = "libraryCard", cascade = CascadeType.ALL)
    List<Transaction>transactionList=new ArrayList<>();
}
