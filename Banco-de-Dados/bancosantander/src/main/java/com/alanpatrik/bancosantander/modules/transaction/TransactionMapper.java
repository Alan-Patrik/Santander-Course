package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionAccountDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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

//    default TransactionRequestDTO toTransactionRequestDTO(Transaction transaction) {
//        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
//        transactionRequestDTO.setValue(transaction.getValue());
//        transactionRequestDTO.setTransactionType(transaction.getTransactionType());
//        transactionRequestDTO.setNumber(transaction.getNumber());
//        transactionRequestDTO.setAgency(transaction.getAgency());
//        transactionRequestDTO.setAccountId(transaction.getAccount().getId());
//
//        return transactionRequestDTO;
//    }

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
}
