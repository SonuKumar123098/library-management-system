package com.example.librarymanagementsystem.modal;

import com.example.librarymanagementsystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table(name = "student-info")
public class Student {
//    @Column(name="studentId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;
    @Enumerated(EnumType.STRING)
    Gender gender;
//    @Column(name="student-name")
    @Column(nullable = false,name="student_name")
    String name;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    int age;
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    LibraryCard libraryCard;

}
