package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import com.example.librarymanagementsystem.dto.responseDTO.IssueBookResponse;
import com.example.librarymanagementsystem.modal.Book;
import com.example.librarymanagementsystem.modal.LibraryCard;
import com.example.librarymanagementsystem.modal.Student;
import com.example.librarymanagementsystem.modal.Transaction;

import java.util.UUID;

public class TransactionTransformer {
    public static Transaction CreateTransaction(Book book, LibraryCard libraryCard){
        return Transaction.builder()
                .transactionStatus(TransactionStatus.SUCCESS)
                .book(book)
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .libraryCard(libraryCard)
                .build();
    }
    public static IssueBookResponse CreateIssueBookResponse(Transaction transaction){
        return IssueBookResponse.builder()
                .libraryCardNumber(transaction.getLibraryCard().getCardNo())
                .issueDate(transaction.getTransactiontime())
                .studentname(transaction.getLibraryCard().getStudent().getName())
                .title(transaction.getBook().getTitle())
                .authorName(transaction.getBook().getAuthor().getName())
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.SUCCESS)
                .build();
    }
}
