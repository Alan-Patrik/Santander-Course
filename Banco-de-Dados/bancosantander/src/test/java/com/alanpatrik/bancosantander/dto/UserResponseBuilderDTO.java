package com.alanpatrik.bancosantander.dto;

import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.user.dto.UserRequestDTO;
import com.alanpatrik.bancosantander.modules.user.dto.UserResponseDTO;
import lombok.Builder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public class UserResponseBuilderDTO {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String cpf = "123.456.789-11";

    @Builder.Default
    private String name = "João Carlinhos";

    @Builder.Default
    private LocalDateTime descriptionDate = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updateDate = LocalDateTime.now();

    @Builder.Default
    private List<Account> accounts = null;

    public UserResponseDTO buildResponseDTO() {
        return new UserResponseDTO(id, cpf, name, descriptionDate, updateDate, accounts);
    }
}
