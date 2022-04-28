package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.clients.dto.TransactionDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionAccountDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);
    UserMapper USER_INSTANCE = Mappers.getMapper(UserMapper.class);

    default TransactionResponseDTO toDTO(Transaction transaction) {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setId(transaction.getId());
        transactionResponseDTO.setValue(transaction.getValue());
        transactionResponseDTO.setTransactionType(transaction.getTransactionType());
        transactionResponseDTO.setNumber(transaction.getNumber());
        transactionResponseDTO.setAgency(transaction.getAgency());
        transactionResponseDTO.setDescriptionDate(transaction.getDescriptionDate());
        transactionResponseDTO.setUpdateDate(transaction.getUpdateDate());

        var user = USER_INSTANCE.toUserAccountDTO(transaction.getAccount().getUser());
        var transactionAccountDTO = toTransactionAccountDTO(transaction.getAccount(), user);

        transactionResponseDTO.setDestination(transactionAccountDTO);

        return transactionResponseDTO;
    }

    default TransactionAccountDTO toTransactionAccountDTO(Account account, UserAccountDTO userAccountDTO) {
        TransactionAccountDTO transactionAccountDTO = new TransactionAccountDTO();
        transactionAccountDTO.setNumber(account.getNumber());
        transactionAccountDTO.setAgency(account.getAgency());
        transactionAccountDTO.setAccountType(account.getAccountType());
        transactionAccountDTO.setUser(userAccountDTO);

        return transactionAccountDTO;
    }

    default TransactionResponseDTO toTransactionResponseDTO(Transaction transaction, TransactionAccountDTO transactionAccountDTO) {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setId(transaction.getId());
        transactionResponseDTO.setValue(transaction.getValue());
        transactionResponseDTO.setTransactionType(transaction.getTransactionType());
        transactionResponseDTO.setNumber(transaction.getNumber());
        transactionResponseDTO.setAgency(transaction.getAgency());
        transactionResponseDTO.setDescriptionDate(transaction.getDescriptionDate());
        transactionResponseDTO.setUpdateDate(transaction.getUpdateDate());
        transactionResponseDTO.setDestination(transactionAccountDTO);

        return transactionResponseDTO;
    }

    default TransactionResponseDTO fromTransactionDTOToTransactionResponseDTO(
            TransactionDTO transactionDTO, TransactionAccountDTO transactionAccountDTO) {
        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        transactionResponseDTO.setId(transactionDTO.getId());
        transactionResponseDTO.setNumber(transactionDTO.getNumber());
        transactionResponseDTO.setAgency(transactionDTO.getAgency());
        transactionResponseDTO.setDescriptionDate(transactionDTO.getDescriptionDate());
        transactionResponseDTO.setValue(transactionDTO.getValue());
        transactionResponseDTO.setTransactionType(transactionDTO.getTransactionType());
        transactionResponseDTO.setDestination(transactionAccountDTO);

        return transactionResponseDTO;
    }

    default List<TransactionResponseDTO> toResponseDTO(
            List<TransactionDTO> transactionDTOList, TransactionAccountDTO transactionAccountDTO
    ){
        List<TransactionResponseDTO> transactionResponseDTOList = new ArrayList<>();

        for (TransactionDTO transactionDTO : transactionDTOList) {
            TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
            transactionResponseDTO.setId(transactionDTO.getId());
            transactionResponseDTO.setValue(transactionDTO.getValue());
            transactionResponseDTO.setTransactionType(transactionDTO.getTransactionType());
            transactionResponseDTO.setNumber(transactionDTO.getNumber());
            transactionResponseDTO.setAgency(transactionDTO.getAgency());
            transactionResponseDTO.setDescriptionDate(transactionDTO.getDescriptionDate());
            transactionResponseDTO.setDestination(transactionAccountDTO);

            transactionResponseDTOList.add(transactionResponseDTO);
        }

        return transactionResponseDTOList;
    }
}
