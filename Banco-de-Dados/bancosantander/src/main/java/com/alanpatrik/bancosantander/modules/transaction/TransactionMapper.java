package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.modules.clients.dto.TransactionDTOList;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionAccountDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    default List<TransactionResponseDTO> toResponseDTOList(
            List<TransactionDTOList> transactionList, TransactionAccountDTO transactionAccountDTO
    ) {
        List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();

        for (TransactionDTOList transactionDTOList : transactionList) {

            TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setId(transactionDTOList.getId());
            transactionResponseDTO.setValue(transactionDTOList.getValue());
            transactionResponseDTO.setTransactionType(transactionDTOList.getTransactionType());
            transactionResponseDTO.setDescriptionDate(transactionDTOList.getDescriptionDate());
            transactionResponseDTO.setSenderAccountId(transactionDTOList.getSenderAccount().getId());
            transactionResponseDTO.setDestinationAccountId(transactionDTOList.getDestinationAccount().getId());

            transactionResponseDTOList.add(transactionResponseDTO);
        }

        return transactionResponseDTOList;
    }
}
