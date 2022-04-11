package com.alanpatrik.bancosantander.repositories;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.enums.TransactionType;
import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.transaction.Transaction;
import com.alanpatrik.bancosantander.modules.transaction.TransactionRepository;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void validando_findAll_deve_passar() {
        var transaction = transactionRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), transaction);
    }

    @Test
    public void retornando_todas_as_transacoes_cadastradas_deve_passar() {
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

        account = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setValue(100.00);
        transaction.setTransactionType(TransactionType.DEPOSITO);
        transaction.setNumber(1111111);
        transaction.setAgency(1111);
        transaction.setAccount(account);

        entityManager.persist(transaction);

        var transactionList = transactionRepository.findAll();

        Assertions.assertNotNull(transactionList);
    }

    @Test
    public void retornar_transacao_por_periodo_deve_passar() {
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

        account = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setValue(100.00);
        transaction.setTransactionType(TransactionType.DEPOSITO);
        transaction.setNumber(1111111);
        transaction.setAgency(1111);
        transaction.setAccount(account);

        transaction = entityManager.persist(transaction);

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.Direction.ASC, "numero");

        var returnedTransaction = transactionRepository.searchByMonth(04, pageRequest);

        Assertions.assertEquals(transaction.getTransactionType(), returnedTransaction.getContent().get(0).getTransactionType());
        Assertions.assertEquals("Alan Patrik", returnedTransaction.getContent().get(0).getAccount().getUser().getName());
    }

    @Test
    public void cadastrando_transacao_deposito_deve_passar() {
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

        account = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setValue(100.00);
        transaction.setTransactionType(TransactionType.DEPOSITO);
        transaction.setNumber(1111111);
        transaction.setAgency(1111);
        transaction.setAccount(account);

        account.setBalance(account.getBalance() + transaction.getValue());
        account = accountRepository.save(account);

        transaction = entityManager.persist(transaction);


        var returnedTransaction = transactionRepository.findById(transaction.getId());

        Assertions.assertEquals(transaction.getTransactionType(), returnedTransaction.get().getTransactionType());
        Assertions.assertEquals(200.0, account.getBalance());
        Assertions.assertEquals("Alan Patrik", returnedTransaction.get().getAccount().getUser().getName());
    }

    @Test
    public void cadastrando_transacao_transferencia_deve_passar() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("123456");

        User user2 = new User();
        user2.setName("Ana");
        user2.setCpf("222.222.222-22");
        user2.setPassword("654321");

        user = userRepository.save(user);
        user2 = userRepository.save(user2);

        Account account = new Account();
        account.setNumber(1111111);
        account.setAgency(1111);
        account.setBalance(100.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);
        account.setUser(user);

        Account account2 = new Account();
        account2.setNumber(2222222);
        account2.setAgency(2222);
        account2.setBalance(100.00);
        account2.setAccountType(AccountType.CONTA_POUPANCA);
        account2.setUser(user2);

        account = accountRepository.save(account);
        account2 = accountRepository.save(account2);

        Transaction transaction = new Transaction();
        transaction.setValue(100.00);
        transaction.setTransactionType(TransactionType.TRANSFERENCIA);
        transaction.setNumber(1111111);
        transaction.setAgency(1111);
        transaction.setAccount(account2);

        account.setBalance(account.getBalance() - transaction.getValue());
        account2.setBalance(account2.getBalance() + transaction.getValue());

        account = accountRepository.save(account);
        account2 = accountRepository.save(account2);

        transaction = entityManager.persist(transaction);

        var returnedTransaction = transactionRepository.findById(transaction.getId());

        Assertions.assertEquals("TRANSFERENCIA", returnedTransaction.get().getTransactionType().toString());
        Assertions.assertEquals(0.0, account.getBalance());
        Assertions.assertEquals(200.0, account2.getBalance());
    }

    @Test
    public void cadastrando_transacao_saque_deve_passar() {
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

        account = accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setValue(100.00);
        transaction.setTransactionType(TransactionType.SAQUE);
        transaction.setNumber(1111111);
        transaction.setAgency(1111);
        transaction.setAccount(account);

        account.setBalance(account.getBalance() - transaction.getValue());
        account = accountRepository.save(account);

        transaction = entityManager.persist(transaction);


        var returnedTransaction = transactionRepository.findById(transaction.getId());

        Assertions.assertEquals(transaction.getTransactionType(), returnedTransaction.get().getTransactionType());
        Assertions.assertEquals(0.0, account.getBalance());
        Assertions.assertEquals("Alan Patrik", returnedTransaction.get().getAccount().getUser().getName());
    }
}
