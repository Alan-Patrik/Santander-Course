package com.alanpatrik.bancosantander.dto;

import com.alanpatrik.bancosantander.modules.user.dto.UserRequestDTO;
import lombok.Builder;

@Builder
public class UserRequestBuilderDTO {

    @Builder.Default
    private String name = "João Carlinhos";

    @Builder.Default
    private String cpf = "123.456.789-11";

    @Builder.Default
    private String password = "123456";

    public UserRequestDTO buildRequestDTO() {
        return new UserRequestDTO(name, cpf, password);
    }
}
