package com.alanpatrik.bancosantander.repositories;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void validando_findAll_deve_passar() {
        var account = accountRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), account);
    }

    @Test
    public void retornando_todas_as_contas_cadastradas_deve_passar() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("123456");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);
        account.setUser(user);

        entityManager.persist(account);

        var accounts = accountRepository.findAll();

        Assertions.assertNotNull(accounts);
    }

    @Test
    public void retornar_conta_pelo_numero_deve_passar() {
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
        account.setUser(user);

        entityManager.persist(account);

        Optional<Account> returnedAccount = accountRepository.findByNumber(account.getNumber());

        Assertions.assertEquals(1111111, returnedAccount.get().getNumber());
        Assertions.assertEquals("Alan Patrik", returnedAccount.get().getUser().getName());
    }

    @Test
    public void cadastrando_conta_deve_passar() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("123456");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);
        account.setUser(user);

        entityManager.persist(account);

        Optional<Account> returnedAccount = accountRepository.findById(account.getId());

        Assertions.assertEquals(1111111, returnedAccount.get().getNumber());
        Assertions.assertEquals("Alan Patrik", returnedAccount.get().getUser().getName());
    }

    @Test
    public void cadastrando_conta_com_campo_agencia_invalida_deve_falhar() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("123456");

        Account account = new Account();

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            account.setNumber(1111111);
            account.setAgency(1);
            account.setBalance(100.00);
            account.setAccountType(AccountType.CONTA_CORRENTE);
            account.setUser(user);

            accountRepository.save(account);
        });

        String expectedMessage = "No campo AGÊNCIA deve ser no mínimo 2 caracteres.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void atualizando_conta_e_retornando_atualizada_deve_passar() {
        User user = new User();
        user.setName("Anderson");
        user.setCpf("222.222.222-22");
        user.setPassword("222222");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);
        account.setUser(user);

        entityManager.persist(account);
        account.setBalance(1000.00);

        var updatedAccount = entityManager.persist(account);

        Optional<Account> returnedAccount = accountRepository.findById(account.getId());

        Assertions.assertEquals(1111111, returnedAccount.get().getNumber());
        Assertions.assertEquals(returnedAccount.get().getBalance(), updatedAccount.getBalance());
        Assertions.assertEquals(returnedAccount.get().getUser().getName(), updatedAccount.getUser().getName());
    }

    @Test
    public void atualizando_conta_que_nao_existe_nao_deve_passar() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("123456");

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            Account account = new Account();
            account.setNumber(1111111);
            account.setAgency(1111);
            account.setBalance(100.00);
            account.setAccountType(AccountType.CONTA_CORRENTE);
            account.setUser(user);

            entityManager.persist(account);

            Optional<Account> updatedAccount = accountRepository.findById(2L);
            updatedAccount.get().setNumber(1111111);
            updatedAccount.get().setAgency(1111);
            updatedAccount.get().setBalance(1000.00);
            updatedAccount.get().setAccountType(AccountType.CONTA_CORRENTE);
            updatedAccount.get().setUser(user);

            accountRepository.save(updatedAccount.get());
        });

        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deletando_conta_deve_passar() {
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
        account.setUser(user);

        account = entityManager.persist(account);
        accountRepository.delete(account);

        var accountList = accountRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), accountList);
    }
}
