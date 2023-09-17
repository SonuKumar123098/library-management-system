package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.Enum.Gender;
import com.example.librarymanagementsystem.modal.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //defining custom function to operate on database
    List<Student> getByGender(Gender gender);


}
