package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import org.springframework.data.domain.Page;

public interface TransactionService {

    Page<TransactionResponseDTO> getAll(int page, int size, String sort);

    TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO) throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException;
}
