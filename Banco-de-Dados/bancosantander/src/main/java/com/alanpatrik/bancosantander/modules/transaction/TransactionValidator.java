package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.modules.account.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionValidator {

    private Account senderAccount;
    private Account destinationAccount;
    private Transaction transaction;
}
