package com.alanpatrik.bancosantander.modules.account;

import com.alanpatrik.bancosantander.modules.account.dto.AccountRequestDTO;
import com.alanpatrik.bancosantander.modules.account.dto.AccountResponseDTO;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    UserMapper USER_INSTANCE = Mappers.getMapper(UserMapper.class);

    default AccountResponseDTO toDTO(Account account) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setId(account.getId());
        accountResponseDTO.setNumber(account.getNumber());
        accountResponseDTO.setAgency(account.getAgency());
        accountResponseDTO.setDescriptionDate(account.getDescriptionDate());
        accountResponseDTO.setUpdateDate(account.getUpdateDate());
        accountResponseDTO.setBalance(account.getBalance());
        accountResponseDTO.setAccountType(account.getAccountType());

        UserAccountDTO userAccountDTO = USER_INSTANCE.toUserAccountDTO(account.getUser());
        accountResponseDTO.setUser(userAccountDTO);

        return accountResponseDTO;
    }

//    default Account fromAccountResponseDTOToModel(AccountResponseDTO accountResponseDTO) {
//        Account account = new Account();
//        account.setId(accountResponseDTO.getId());
//        account.setNumber(accountResponseDTO.getNumber());
//        account.setAgency(accountResponseDTO.getAgency());
//        account.setDescriptionDate(accountResponseDTO.getDescriptionDate());
//        account.setUpdateDate(accountResponseDTO.getUpdateDate());
//        account.setBalance(accountResponseDTO.getBalance());
//        account.setAccountType(accountResponseDTO.getAccountType());
//
//        User user = USER_INSTANCE.toModel(accountResponseDTO.getUser());
//        account.setUser(user);
//
//        return account;
//    }

    default AccountResponseDTO fromDTOToAccountResponseDTO(Account account, UserAccountDTO userAccountDTO) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        accountResponseDTO.setId(account.getId());
        accountResponseDTO.setNumber(account.getNumber());
        accountResponseDTO.setAgency(account.getAgency());
        accountResponseDTO.setDescriptionDate(account.getDescriptionDate());
        accountResponseDTO.setUpdateDate(account.getUpdateDate());
        accountResponseDTO.setBalance(account.getBalance());
        accountResponseDTO.setAccountType(account.getAccountType());
        accountResponseDTO.setUser(userAccountDTO);

        return accountResponseDTO;
    }

    default AccountRequestDTO toRequestDTO(Account account) {
        AccountRequestDTO accountRequestDTO = new AccountRequestDTO();
        accountRequestDTO.setNumber(account.getNumber());
        accountRequestDTO.setAgency(account.getAgency());
        accountRequestDTO.setBalance(account.getBalance());
        accountRequestDTO.setAccountType(account.getAccountType());
        accountRequestDTO.setUserId(account.getId());

        return accountRequestDTO;
    }
}
