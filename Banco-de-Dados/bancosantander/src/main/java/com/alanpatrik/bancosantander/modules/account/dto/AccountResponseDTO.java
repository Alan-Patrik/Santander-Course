package com.alanpatrik.bancosantander.modules.account.dto;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponseDTO {

    private Long id;
    private Integer number;
    private Integer agency;
    private LocalDateTime descriptionDate;
    private LocalDateTime updateDate;
    private double balance;
    private AccountType accountType;
    private UserAccountDTO user;
}
