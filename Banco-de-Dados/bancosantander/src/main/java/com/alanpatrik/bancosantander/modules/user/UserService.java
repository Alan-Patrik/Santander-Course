package com.alanpatrik.bancosantander.modules.user;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.user.dto.UserRequestDTO;
import com.alanpatrik.bancosantander.modules.user.dto.UserResponseDTO;
import org.springframework.data.domain.Page;

public interface UserService {

    User verifyIfUserExists(Long id) throws CustomNotFoundException;

    void verifyIfUserAlreadyExists(String cpf) throws CustomBadRequestException;

    UserResponseDTO getById(Long id) throws CustomNotFoundException;

    Page<UserResponseDTO> getAll(int page, int size, String sort);

    Page<UserResponseDTO> searchByName(String name, int page, int size, String sort);

    UserResponseDTO create(UserRequestDTO userRequestDTO) throws CustomBadRequestException;

    UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) throws CustomNotFoundException;

    void delete(Long id) throws CustomNotFoundException;
}
