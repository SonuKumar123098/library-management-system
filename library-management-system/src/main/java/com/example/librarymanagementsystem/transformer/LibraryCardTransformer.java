package com.example.librarymanagementsystem.transformer;

import com.example.librarymanagementsystem.Enum.CardStatus;
import com.example.librarymanagementsystem.modal.LibraryCard;

import java.util.UUID;

public class LibraryCardTransformer {
    public static LibraryCard CreateLibraryCard(){
        return LibraryCard.builder()
                .cardStatus(CardStatus.ACTIVE)
                .cardNo(String.valueOf(UUID.randomUUID()))
                .build();
    }
}
