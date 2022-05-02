package com.alanpatrik.bancosantander.modules.account.dto;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
