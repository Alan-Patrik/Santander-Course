package com.alanpatrik.bancosantander.modules.user;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.user.dto.UserRequestDTO;
import com.alanpatrik.bancosantander.modules.user.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User verifyIfUserExists(Long id) throws CustomNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(
                String.format("Usuario com o id %s não foi encontrado.", id)
        ));

        return user;
    }

    @Override
    public void verifyIfUserAlreadyExists(String cpf) throws CustomBadRequestException {
        Optional<User> user = userRepository.findByCpf(cpf);

        if (user.isPresent()) {
            throw new CustomBadRequestException(
                    String.format("Usuario com o CPF %s já foi cadastrado!", cpf)
            );
        }
    }

    @Override
    public UserResponseDTO getById(Long id) throws CustomNotFoundException {
        User user = verifyIfUserExists(id);
        return userMapper.toDTO(user);
    }

    @Override
    public Page<UserResponseDTO> getAll(int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size
        );

        if (sort.equalsIgnoreCase("Asc")) {
            return userRepository.findAll(pageRequest.withSort(Sort.Direction.ASC, "name")).map(userMapper::toDTO);

        } else if (sort.equalsIgnoreCase("Desc")) {
            return userRepository.findAll(pageRequest.withSort(Sort.Direction.DESC, "name")).map(userMapper::toDTO);

        } else {
            return userRepository.findAll(pageRequest).map(userMapper::toDTO);
        }
    }

    @Override
    public Page<UserResponseDTO> searchByName(String name, int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size
        );

        if (name != null & sort.equalsIgnoreCase("Asc")) {
            return userRepository.findByName(name, pageRequest.withSort(Sort.Direction.ASC, "name")).map(userMapper::toDTO);

        } else if (name != null & sort.equalsIgnoreCase("Desc")) {
            return userRepository.findByName(name, pageRequest.withSort(Sort.Direction.DESC, "name")).map(userMapper::toDTO);

        } else {
            return userRepository.findAll(pageRequest).map(userMapper::toDTO);
        }
    }

    @Override
    public UserResponseDTO create(UserRequestDTO object) throws CustomBadRequestException {
        verifyIfUserAlreadyExists(object.getCpf());

        User user = new User();
        user.setCpf(object.getCpf());
        user.setName(object.getName());
        user.setPassword(object.getPassword());

        user = userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO object) throws CustomNotFoundException {
        User user = verifyIfUserExists(id);

        user.setCpf(object.getCpf());
        user.setName(object.getName());
        user.setPassword(object.getPassword());

        user = userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public void delete(Long id) throws CustomNotFoundException {
        User user = verifyIfUserExists(id);
        userRepository.delete(user);
    }
}
