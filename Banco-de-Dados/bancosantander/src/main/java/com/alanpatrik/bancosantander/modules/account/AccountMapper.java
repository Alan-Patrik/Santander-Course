package com.alanpatrik.bancosantander.modules.account;

import com.alanpatrik.bancosantander.modules.account.dto.AccountRequestDTO;
import com.alanpatrik.bancosantander.modules.account.dto.AccountResponseDTO;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import org.mapstruct.Mapper;
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
