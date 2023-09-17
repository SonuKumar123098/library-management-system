package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.exception.BookNotAvailableException;
import com.example.librarymanagementsystem.exception.StudentNotFoundException;
import com.example.librarymanagementsystem.modal.Book;
import com.example.librarymanagementsystem.modal.LibraryCard;
import com.example.librarymanagementsystem.modal.Student;
import com.example.librarymanagementsystem.modal.Transaction;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.StudentRepository;
import com.example.librarymanagementsystem.repository.TransactionRepository;
import com.example.librarymanagementsystem.service.TransactionService;
import com.example.librarymanagementsystem.transformer.TransactionTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public IssueBookResponse issueBook(int bookId, int studentId) {
        Optional<Book> bookOptional=bookRepository.findById(bookId);
        if(bookOptional.isEmpty()){
            throw new BookNotAvailableException("invalid bookId!");
        }
        Book book=bookOptional.get();
        if(book.isIssued()){
            throw new BookNotAvailableException("book already issued!");
        }
        Optional<Student> studentOptional=studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new StudentNotFoundException("student not found!");
        }
        Student student=studentOptional.get();
        book.setIssued(true);
        LibraryCard libraryCard=student.getLibraryCard();

        //save to transaction repository
        Transaction transaction= TransactionTransformer.CreateTransaction(book,libraryCard);
        Transaction savedTransaction= transactionRepository.save(transaction);

        //set librarycard's transaction
        libraryCard.getTransactionList().add(savedTransaction);
        book.getTransactionList().add(savedTransaction);

        //save to bookRepository ans studentRepository
        Book savedBook=bookRepository.save(book);
        Student savedStudent=studentRepository.save(student);

        // create a issueBookResponse

        return TransactionTransformer.CreateIssueBookResponse(savedTransaction);
    }

//    @Override
//    public IssueBookResponse returnBook(int bookId) {
//        Book book=bookRepository.getById(bookId);
//        book.setIssued(false);
//        Transaction transaction=book.getTransactionList().get(book.getTransactionList().size()-1);
//        LibraryCard libraryCard=transaction.getLibraryCard();
//    }
}
