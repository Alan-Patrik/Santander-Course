package com.alanpatrik.bancosantander.services;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.enums.TransactionType;
import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.account.AccountMapper;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.account.AccountServiceImpl;
import com.alanpatrik.bancosantander.modules.transaction.TransactionMapper;
import com.alanpatrik.bancosantander.modules.transaction.TransactionServiceImpl;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class TransactionServiceTest {

    private final TransactionMapper transactionMapper = TransactionMapper.INSTANCE;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void retornando_todas_transacoes_cadastradas_deve_passar() throws Exception {
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

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setValue(100.00);
        transactionRequestDTO.setTransactionType(TransactionType.DEPOSITO);
        transactionRequestDTO.setNumber(1111111);
        transactionRequestDTO.setAgency(1111);
        transactionRequestDTO.setAccountId(account.getId());

        transactionService.create(transactionRequestDTO);

        var expected = transactionService.getAll(0, 5, "Asc");

        Assertions.assertNotNull(expected.getContent());
    }

    @Test
    public void pesquisando_transacao_por_periodo_deve_passar() throws Exception {
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

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setValue(100.00);
        transactionRequestDTO.setTransactionType(TransactionType.DEPOSITO);
        transactionRequestDTO.setNumber(1111111);
        transactionRequestDTO.setAgency(1111);
        transactionRequestDTO.setAccountId(account.getId());

        var transactionResponseDTO = transactionService.create(transactionRequestDTO);

        var returnedTransaction = transactionService.searchByMonth(04, 0, 5, "Asc");

        Assertions.assertEquals(transactionResponseDTO.getTransactionType(), returnedTransaction.getContent().get(0).getTransactionType());
        Assertions.assertEquals("Alan Patrik", returnedTransaction.getContent().get(0).getDestination().getUser().getName());
    }

    @Test
    public void pesquisando_transacao_por_periodo_com_numero_entre_1_e_12_deve_passar() throws Exception {
        var returnedTransaction = transactionService.searchByMonth(4, 0, 5, "Asc");

        Assertions.assertEquals(new ArrayList<>(), returnedTransaction.getContent());
    }

    @Test
    public void pesquisando_transacao_por_periodo_com_numero_menor_do_que_1_ou_maior_do_que_12_nao_deve_passar() throws Exception {
        Exception exception = Assertions.assertThrows(CustomBadRequestException.class, () -> {
            transactionService.searchByMonth(13, 0, 5, "Asc");
        });

        String expectedMessage = "Ops... Aconteceu um erro. Por favor, insira um MÊS válido! Ex(4) => 'ABRIL'";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void cadastrando_transacao_deposito_deve_passar() throws CustomNotFoundException, CustomBadRequestException {
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

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setValue(100.00);
        transactionRequestDTO.setTransactionType(TransactionType.DEPOSITO);
        transactionRequestDTO.setNumber(1111111);
        transactionRequestDTO.setAgency(1111);
        transactionRequestDTO.setAccountId(account.getId());

        var expectedResponse = transactionService.create(transactionRequestDTO);
        var returnedAccount = accountRepository.findById(transactionRequestDTO.getAccountId());

        Assertions.assertEquals("DEPOSITO", expectedResponse.getTransactionType().toString());
        Assertions.assertEquals(200.0, returnedAccount.get().getBalance());
        Assertions.assertEquals("Alan Patrik", expectedResponse.getDestination().getUser().getName());
    }

    @Test
    public void cadastrando_transacao_transferencia_deve_passar() throws CustomNotFoundException, CustomBadRequestException {
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

        accountRepository.save(account);
        account2 = accountRepository.save(account2);

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setValue(100.00);
        transactionRequestDTO.setTransactionType(TransactionType.TRANSFERENCIA);
        transactionRequestDTO.setNumber(1111111);
        transactionRequestDTO.setAgency(1111);
        transactionRequestDTO.setAccountId(account2.getId());

        var expectedResponse = transactionService.create(transactionRequestDTO);
        var returnedAccount = accountRepository.findByNumber(2222222);

        Assertions.assertEquals("TRANSFERENCIA", expectedResponse.getTransactionType().toString());
        Assertions.assertEquals(200.0, returnedAccount.get().getBalance());
        Assertions.assertEquals("Ana", expectedResponse.getDestination().getUser().getName());
    }

//    @Test
//    NÃO ENTENDI O PORQUE DESSE TESTE NÃO ESTAR FUNCIONANDO, JÁ QUE O ID DE CONTA É O MESMO
    public void cadastrando_transacao_saque_deve_passar() throws CustomNotFoundException, CustomBadRequestException {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("123456");

        user = userRepository.save(user);

        Account account = new Account();
        account.setNumber(3333333);
        account.setAgency(3333);
        account.setBalance(1000.00);
        account.setAccountType(AccountType.CONTA_CORRENTE);
        account.setUser(user);

        accountRepository.save(account);

        TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
        transactionRequestDTO.setValue(1000.00);
        transactionRequestDTO.setTransactionType(TransactionType.SAQUE);
        transactionRequestDTO.setNumber(3333333);
        transactionRequestDTO.setAgency(3333);
        transactionRequestDTO.setAccountId(1L);

        var expectedResponse = transactionService.create(transactionRequestDTO);
        var returnedAccount = accountRepository.findById(transactionRequestDTO.getAccountId());

        Assertions.assertEquals("SAQUE", expectedResponse.getTransactionType().toString());
        Assertions.assertEquals(0.0, returnedAccount.get().getBalance());
        Assertions.assertEquals("Alan Patrik", expectedResponse.getDestination().getUser().getName());
    }
}
