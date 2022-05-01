package com.alanpatrik.bancosantander.modules.transaction.dto;

import com.alanpatrik.bancosantander.enums.TransactionType;
import com.alanpatrik.bancosantander.modules.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private double value;
    private TransactionType transactionType;
    private Account senderAccount;
    private Account destinationAccount;
}
