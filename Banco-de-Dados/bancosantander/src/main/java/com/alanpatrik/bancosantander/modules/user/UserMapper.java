package com.alanpatrik.bancosantander.modules.user;

import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import com.alanpatrik.bancosantander.modules.user.dto.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default UserResponseDTO toDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setCpf(user.getCpf());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setDescriptionDate(user.getDescriptionDate());
        userResponseDTO.setUpdateDate(user.getUpdateDate());
        userResponseDTO.setAccounts(user.getAccounts());

        return userResponseDTO;
    }

    default UserAccountDTO toUserAccountDTO(User user) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setCpf(user.getCpf());
        userAccountDTO.setName(user.getName());

        return userAccountDTO;
    }

//    default User toModel(UserAccountDTO userAccountDTO) {
//        User user = new User();
//        user.setCpf(userAccountDTO.getCpf());
//        user.setName(userAccountDTO.getName());
//
//        return user;
//    }
}
