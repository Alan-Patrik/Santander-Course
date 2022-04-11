package com.alanpatrik.bancosantander.services;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.account.AccountMapper;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.account.AccountServiceImpl;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    @Test
    public void retornando_todas_contas_cadastradas_deve_passar() throws Exception {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);

        var accountRequestDTO = accountMapper.toRequestDTO(account);

        accountService.create(user.getId(), accountRequestDTO);

        var expected = accountRepository.findAll();

        Assertions.assertNotNull(expected);
    }

    @Test
    public void verificando_se_conta_existe_deve_passar() throws CustomNotFoundException, CustomBadRequestException {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);

        var accountRequestDTO = accountMapper.toRequestDTO(account);

        var registeredAccount = accountService.create(user.getId(), accountRequestDTO);

        var expectedResponse = accountService.verifyIfAccountExists(registeredAccount.getId());

        Assertions.assertEquals(expectedResponse.getId(), registeredAccount.getId());
    }

    @Test
    public void retornando_conta_que_nao_existe_nao_deve_passar() {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            accountService.verifyIfAccountExists(1L);
        });

        String expectedMessage = "Conta com o id 1 não foi encontrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void verificando_se_conta_existe_por_id_deve_passar() throws CustomNotFoundException, CustomBadRequestException {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);

        var accountRequestDTO = accountMapper.toRequestDTO(account);

        var registeredAccount = accountService.create(user.getId(), accountRequestDTO);

        var expectedResponse = accountService.getById(registeredAccount.getId());

        Assertions.assertEquals(expectedResponse.getId(), registeredAccount.getId());
    }

    @Test
    public void verificando_se_conta_existe_por_id_nao_deve_passar() {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            accountService.verifyIfAccountExists(1L);
        });

        String expectedMessage = "Conta com o id 1 não foi encontrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void pesquisando_conta_pelo_numero_deve_passar() throws Exception {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);

        var accountRequestDTO = accountMapper.toRequestDTO(account);

        var registeredAccount = accountService.create(user.getId(), accountRequestDTO);

        var expectedResponse = accountService.getByNumber(registeredAccount.getNumber());

        Assertions.assertNotNull(expectedResponse);
        Assertions.assertEquals(1111111, expectedResponse.getNumber());
    }

    @Test
    public void pesquisando_conta_pelo_numero_que_nao_existe_nao_deve_passar() throws Exception {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            accountService.getByNumber(1111111);
        });

        String expectedMessage = "Conta com o numero 1111111 não foi cadastrada";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void cadastrando_conta_valida_deve_passar() throws CustomBadRequestException, CustomNotFoundException {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);

        var accountRequestDTO = accountMapper.toRequestDTO(account);

        var registeredAccount = accountService.create(user.getId(), accountRequestDTO);

        var expectedResponse = accountService.verifyIfAccountExists(registeredAccount.getId());

        Assertions.assertEquals(expectedResponse.getId(), registeredAccount.getId());
        Assertions.assertEquals(expectedResponse.getUser().getName(), registeredAccount.getUser().getName());
    }

    @Test
    public void cadastrando_conta_sem_colocar_agencia_nao_deve_passar() throws CustomBadRequestException {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            User user = new User();
            user.setName("Alan Patrik");
            user.setCpf("111.111.111-11");
            user.setPassword("111111");

            user = userRepository.save(user);

            Account account = new Account();
            account.setNumber(1111111);
            account.setBalance(100.00);
            account.setAccountType(AccountType.CONTA_CORRENTE);

            var accountRequestDTO = accountMapper.toRequestDTO(account);

            accountService.create(user.getId(), accountRequestDTO);
        });

        String expectedMessage = "Campo AGÊNCIA é obrigatório!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void cadastrando_conta_com_agencia_invalida_nao_deve_passar() throws CustomBadRequestException {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            User user = new User();
            user.setName("Alan Patrik");
            user.setCpf("111.111.111-11");
            user.setPassword("111111");

            user = userRepository.save(user);

            Account account = new Account();
            account.setNumber(1111111);
            account.setAgency(1);
            account.setBalance(100.00);
            account.setAccountType(AccountType.CONTA_CORRENTE);

            var accountRequestDTO = accountMapper.toRequestDTO(account);

            accountService.create(user.getId(), accountRequestDTO);
        });

        String expectedMessage = "No campo AGÊNCIA deve ser no mínimo 2 caracteres.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void atualizando_conta_deve_passar() throws Exception {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);

        var accountRequestDTO = accountMapper.toRequestDTO(account);

        var accountResponseDTO = accountService.create(user.getId(), accountRequestDTO);

        var registeredAccount = accountService.verifyIfAccountExists(accountResponseDTO.getId());
        registeredAccount.setBalance(1000.00);

        var expectedResponse = accountService.update(accountResponseDTO.getId(), accountMapper.toRequestDTO(registeredAccount));

        Assertions.assertEquals(expectedResponse.getId(), registeredAccount.getId());
        Assertions.assertEquals(1000.0, expectedResponse.getBalance());
        Assertions.assertEquals(expectedResponse.getUser().getName(), registeredAccount.getUser().getName());
    }

    @Test
    public void atualizando_conta_que_nao_existe_nao_deve_passar() throws Exception {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            User user = new User();
            user.setName("Alan Patrik");
            user.setCpf("111.111.111-11");
            user.setPassword("111111");

            user = userRepository.save(user);

            Account account = new Account();
            account.setNumber(1111111);
            account.setAgency(1111);
            account.setBalance(100.00);
            account.setAccountType(AccountType.CONTA_CORRENTE);

            var accountRequestDTO = accountMapper.toRequestDTO(account);

            var accountResponseDTO = accountService.create(user.getId(), accountRequestDTO);

            var registeredAccount = accountService.verifyIfAccountExists(accountResponseDTO.getId());
            registeredAccount.setBalance(1000.00);

            accountService.update(10L, accountMapper.toRequestDTO(registeredAccount));
        });

        String expectedMessage = "Ops... Aconteceu um erro. Conta com o id 10 não foi encontrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deletando_conta_por_id_deve_passar() throws Exception {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);

        var accountRequestDTO = accountMapper.toRequestDTO(account);

        var accountResponseDTO = accountService.create(user.getId(), accountRequestDTO);

        accountService.delete(accountResponseDTO.getId());

        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            accountService.verifyIfAccountExists(1L);
        });

        String expectedMessage = "Conta com o id 1 não foi encontrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deletando_conta_por_id_que_nao_existe_nao_deve_passar() throws Exception {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            accountService.delete(1L);
        });

        String expectedMessage = "Conta com o id 1 não foi encontrada.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
