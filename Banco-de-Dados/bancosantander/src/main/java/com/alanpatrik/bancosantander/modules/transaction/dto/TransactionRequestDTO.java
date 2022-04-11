package com.alanpatrik.bancosantander.modules.transaction.dto;

import com.alanpatrik.bancosantander.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {

    private double value;
    private TransactionType transactionType;
    private int number;
    private int agency;
    private Long accountId;
}
