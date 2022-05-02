package com.alanpatrik.bancosantander.modules.account;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.dto.AccountRequestDTO;
import com.alanpatrik.bancosantander.modules.account.dto.AccountResponseDTO;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper = AccountMapper.INSTANCE;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Account verifyIfAccountExists(Long id) throws CustomNotFoundException {
        return accountRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(
                String.format("Conta com o id %s não foi encontrada.", id)
        ));
    }

    @Override
    public void verifyIfAccountAlreadyExists(Integer number) throws CustomBadRequestException {
        Optional<Account> account = accountRepository.findByNumber(number);

        if (account.isPresent()) {
            throw new CustomBadRequestException(
                    String.format("Conta com o numero %s já cadastrada", number)
            );
        }
    }

    @Override
    public AccountResponseDTO getById(Long id) throws CustomNotFoundException {
        Account account = verifyIfAccountExists(id);

        return accountMapper.toDTO(account);
    }

    @Override
    public AccountResponseDTO getByNumber(Integer accountNumber) throws CustomBadRequestException, CustomNotFoundException {
        Account account = accountRepository.findByNumber(accountNumber).orElseThrow(() -> new CustomNotFoundException(
                String.format("Conta com o numero %s não foi cadastrada", accountNumber)
        ));

        return accountMapper.toDTO(account);
    }

    @Override
    public Page<AccountResponseDTO> getAll(int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size
        );

        if (sort.equalsIgnoreCase("Asc")) {
            return accountRepository.findAll(pageRequest.withSort(Sort.Direction.ASC, "number")).map(accountMapper::toDTO);

        } else if (sort.equalsIgnoreCase("Desc")) {
            return accountRepository.findAll(pageRequest.withSort(Sort.Direction.DESC, "number")).map(accountMapper::toDTO);

        } else {
            return accountRepository.findAll(pageRequest).map(accountMapper::toDTO);
        }
    }

    @Override
    public AccountResponseDTO create(Long id, AccountRequestDTO accountRequestDTO) throws CustomNotFoundException, CustomBadRequestException {
        verifyIfAccountAlreadyExists(accountRequestDTO.getNumber());

        User user = userRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(
                String.format("Usuario com o id %s não foi encontrado.", id)
        ));

        Account account = new Account();
        account.setNumber(accountRequestDTO.getNumber());
        account.setAgency(accountRequestDTO.getAgency());
        account.setBalance(accountRequestDTO.getBalance());
        account.setAccountType(accountRequestDTO.getAccountType());
        account.setUser(user);

        account = accountRepository.save(account);

        UserAccountDTO userAccountDTO = userMapper.toUserAccountDTO(user);

        return accountMapper.fromDTOToAccountResponseDTO(account, userAccountDTO);
    }

    @Override
    public AccountResponseDTO update(Long id, AccountRequestDTO accountRequestDTO) throws CustomNotFoundException {
        Account account = verifyIfAccountExists(id);
        account.setBalance(accountRequestDTO.getBalance());

        account = accountRepository.save(account);

        return accountMapper.toDTO(account);
    }

    @Override
    public void delete(Long id) throws CustomNotFoundException {
        Account account = verifyIfAccountExists(id);
        accountRepository.delete(account);
    }
}
