package com.example.librarymanagementsystem;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
//@Table(name = "student-info")
public class Student {
    @Id
    int regNo;
    @Enumerated(EnumType.STRING)
    Gender gender;
    String email;
    int age;
}
