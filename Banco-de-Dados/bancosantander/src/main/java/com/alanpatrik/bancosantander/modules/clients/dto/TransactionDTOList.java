package com.alanpatrik.bancosantander.modules.clients.dto;

import com.alanpatrik.bancosantander.enums.TransactionType;
import com.alanpatrik.bancosantander.modules.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTOList {

    private Long id;
    private double value;
    private TransactionType transactionType;
    private LocalDateTime descriptionDate;
    private Account senderAccount;
    private Account destinationAccount;
}
