package com.alanpatrik.bancosantander.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String cpf;
    private String name;
    private LocalDateTime descriptionDate;
    private LocalDateTime updateDate;
}
