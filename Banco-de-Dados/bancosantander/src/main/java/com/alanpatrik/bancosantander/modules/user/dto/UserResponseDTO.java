package com.alanpatrik.bancosantander.modules.user.dto;

import lombok.*;

import java.time.LocalDateTime;

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
}
