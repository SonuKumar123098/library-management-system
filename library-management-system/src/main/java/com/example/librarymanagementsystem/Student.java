package com.example.librarymanagementsystem;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "student-info")
public class Student {
    @Id
    int regNo;
    @Enumerated(EnumType.STRING)
    Gender gender;
//    @Column(name="name-info")
    String name;
    String email;
    int age;
}
