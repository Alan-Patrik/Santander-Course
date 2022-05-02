package com.alanpatrik.bancosantander.modules.transaction.dto;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionAccountDTO {

    private Integer number;
    private Integer agency;
    private AccountType accountType;
    private UserAccountDTO user;
}
