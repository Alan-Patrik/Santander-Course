package com.alanpatrik.bancosantander.modules.account.dto;

import com.alanpatrik.bancosantander.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

    private Integer number;
    private Integer agency;
    private double balance;
    private AccountType accountType;
    private Long userId;

}
