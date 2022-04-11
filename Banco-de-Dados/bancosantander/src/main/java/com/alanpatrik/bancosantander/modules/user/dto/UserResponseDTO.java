package com.alanpatrik.bancosantander.modules.user.dto;

import com.alanpatrik.bancosantander.modules.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {

    private Long id;
    private String cpf;
    private String name;
    private LocalDateTime descriptionDate;
    private LocalDateTime updateDate;
    private List<Account> accounts;
}
