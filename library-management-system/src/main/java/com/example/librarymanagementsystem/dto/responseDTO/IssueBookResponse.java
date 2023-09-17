package com.example.librarymanagementsystem.dto.responseDTO;

import com.example.librarymanagementsystem.Enum.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IssueBookResponse {

    String title;

    String studentname;

    Date issueDate;

    String authorName;

    TransactionStatus transactionStatus;

    String libraryCardNumber;

    String transactionNumber;

}
