package com.alanpatrik.bancosantander.modules.clients.dto;

import com.alanpatrik.bancosantander.enums.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private double value;
    private TransactionType transactionType;
    private int number;
    private int agency;
    private LocalDateTime descriptionDate;
    private Long accountId;
}
