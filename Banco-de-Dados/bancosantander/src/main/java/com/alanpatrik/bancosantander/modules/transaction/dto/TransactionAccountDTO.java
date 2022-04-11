package com.alanpatrik.bancosantander.modules.transaction.dto;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionAccountDTO {

    private Integer number;
    private Integer agency;
    private AccountType accountType;
    private UserAccountDTO user;
}
