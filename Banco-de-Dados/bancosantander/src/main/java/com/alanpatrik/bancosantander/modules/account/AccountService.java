package com.alanpatrik.bancosantander.modules.account;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.dto.AccountRequestDTO;
import com.alanpatrik.bancosantander.modules.account.dto.AccountResponseDTO;
import org.springframework.data.domain.Page;

public interface AccountService {

    Account verifyIfAccountExists(Long id) throws CustomNotFoundException;

    void verifyIfAccountAlreadyExists(Integer number) throws CustomBadRequestException;

    AccountResponseDTO getById(Long id) throws CustomNotFoundException;

    AccountResponseDTO getByNumber(Integer accountNumber) throws CustomBadRequestException, CustomNotFoundException;

    Page<AccountResponseDTO> getAll(int page, int size, String sort);

    AccountResponseDTO create(Long id, AccountRequestDTO accountRequestDTO) throws CustomNotFoundException, CustomBadRequestException;

    AccountResponseDTO update(Long id, AccountRequestDTO accountRequestDTO) throws CustomNotFoundException;

    void delete(Long id) throws CustomNotFoundException;
}
