package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.modules.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionValidator {

    private Account senderAccount;
    private Account destinationAccount;
    private Transaction transaction;
}
