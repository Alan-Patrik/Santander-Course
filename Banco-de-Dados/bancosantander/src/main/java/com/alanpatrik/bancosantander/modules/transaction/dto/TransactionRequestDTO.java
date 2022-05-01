package com.alanpatrik.bancosantander.modules.transaction.dto;

import com.alanpatrik.bancosantander.enums.TransactionType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {

    private double value;
    private TransactionType transactionType;
    private int number;
    private int agency;
    private Long accountId;
}
