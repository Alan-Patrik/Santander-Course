package com.alanpatrik.bancosantander.modules.transaction.dto;

import com.alanpatrik.bancosantander.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionResponseDTO {

    private Long id;
    private double value;
    private TransactionType transactionType;
    private int number;
    private int agency;
    private LocalDateTime descriptionDate;
    private LocalDateTime updateDate;
    private TransactionAccountDTO destination;
}
