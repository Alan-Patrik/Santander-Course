package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDTO> getAll() throws CustomInternalServerException, JsonProcessingException;

    TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO) throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException;
}
