package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionAccountDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final TransactionMapper transactionMapper = TransactionMapper.INSTANCE;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private void monthValidator(String month) throws CustomBadRequestException {
        int parser = Integer.parseInt(month);
        if (parser < 1 || parser > 12) {
            throw new CustomBadRequestException("Por favor, insira um MÊS válido! Ex(4) => 'ABRIL'");
        }
    }

    @Override
    public Page<TransactionResponseDTO> searchByMonth(String month, int page, int size, String sort) throws CustomBadRequestException {
        monthValidator(month);

        PageRequest pageRequest = PageRequest.of(
                page,
                size
        );

        if (sort.equalsIgnoreCase("Asc")) {
            return transactionRepository.searchByMonth(month, pageRequest.withSort(Sort.Direction.ASC, "numero")).map(transactionMapper::toDTO);

        } else if (sort.equalsIgnoreCase("Desc")) {
            return transactionRepository.searchByMonth(month, pageRequest.withSort(Sort.Direction.DESC, "numero")).map(transactionMapper::toDTO);

        } else {
            return transactionRepository.searchByMonth(month, pageRequest).map(transactionMapper::toDTO);
        }
    }

    @Override
    public Page<TransactionResponseDTO> getAll(int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size
        );

        if (sort.equalsIgnoreCase("Asc")) {
            return transactionRepository.findAll(pageRequest.withSort(Sort.Direction.ASC, "number")).map(transactionMapper::toDTO);

        } else if (sort.equalsIgnoreCase("Desc")) {
            return transactionRepository.findAll(pageRequest.withSort(Sort.Direction.DESC, "number")).map(transactionMapper::toDTO);

        } else {
            return new PageImpl<>(transactionRepository.findAll(), pageRequest, size).map(transactionMapper::toDTO);
        }
    }

    @Override
    public TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO) throws CustomNotFoundException, CustomBadRequestException {
        Account account = accountRepository.findById(transactionRequestDTO.getAccountId()).orElseThrow(() -> new CustomNotFoundException(
                String.format("Conta com o id %s não foi encontrada.", transactionRequestDTO.getAccountId())
        ));

        Account senderAccount = accountRepository.findByNumber(transactionRequestDTO.getNumber()).orElseThrow(() -> new CustomNotFoundException(
                String.format("Conta com o numero %s não foi encontrada.", transactionRequestDTO.getNumber())
        ));

        Transaction receivedTransaction = new Transaction();
        TransactionValidator transactionValidator = new TransactionValidator();

        switch (transactionRequestDTO.getTransactionType()) {
            case TRANSFERENCIA:
                transactionValidator = transferValidator(senderAccount, account, transactionRequestDTO);

                accountRepository.save(transactionValidator.getSenderAccount());
                accountRepository.save(transactionValidator.getDestinationAccount());
                receivedTransaction = transactionRepository.save(transactionValidator.getTransaction());
                break;

            case DEPOSITO:
                transactionValidator = depositValidator(senderAccount, account, transactionRequestDTO);

                accountRepository.save(transactionValidator.getSenderAccount());
                accountRepository.save(transactionValidator.getDestinationAccount());
                receivedTransaction = transactionRepository.save(transactionValidator.getTransaction());
                break;

            case SAQUE:
                transactionValidator = withdrawValidator(senderAccount, account, transactionRequestDTO);

                accountRepository.save(transactionValidator.getSenderAccount());
                accountRepository.save(transactionValidator.getDestinationAccount());
                receivedTransaction = transactionRepository.save(transactionValidator.getTransaction());
                break;
        }

        TransactionAccountDTO transactionAccountDTO = transactionMapper.toTransactionAccountDTO(
                transactionValidator.getDestinationAccount(),
                userMapper.toUserAccountDTO(transactionValidator.getDestinationAccount().getUser()));

        return transactionMapper.toTransactionResponseDTO(receivedTransaction, transactionAccountDTO);
    }

    private TransactionValidator transferValidator(
            Account senderAccount, Account destinationAccount, TransactionRequestDTO transactionRequestDTO
    ) throws CustomBadRequestException {
        Transaction receivedTransaction = new Transaction();

        if (senderAccount.getNumber().equals(destinationAccount.getNumber())) {
            throw new CustomBadRequestException("Operação recusada. Você não pode efetuar uma transferência para si mesmo!");
        }

        if (senderAccount.getBalance() <= 0) {
            throw new CustomBadRequestException("Saldo insuficiente!");
        }

        receivedTransaction.setValue(transactionRequestDTO.getValue());
        receivedTransaction.setTransactionType(transactionRequestDTO.getTransactionType());
        receivedTransaction.setNumber(transactionRequestDTO.getNumber());
        receivedTransaction.setAgency(transactionRequestDTO.getAgency());
        receivedTransaction.setAccount(destinationAccount);

        senderAccount.setBalance(senderAccount.getBalance() - transactionRequestDTO.getValue());
        destinationAccount.setBalance(destinationAccount.getBalance() + transactionRequestDTO.getValue());

        TransactionValidator transactionValidator = new TransactionValidator();
        transactionValidator.setSenderAccount(senderAccount);
        transactionValidator.setDestinationAccount(destinationAccount);
        transactionValidator.setTransaction(receivedTransaction);

        return transactionValidator;
    }

    private TransactionValidator depositValidator(
            Account senderAccount, Account destinationAccount, TransactionRequestDTO transactionRequestDTO
    ) throws CustomBadRequestException {
        Transaction receivedTransaction = new Transaction();

        if (transactionRequestDTO.getValue() <= 0) {
            throw new CustomBadRequestException("Não é possível depositar valor menor ou igual a 0!");
        }

        if (senderAccount.getNumber().equals(destinationAccount.getNumber())) {
            receivedTransaction.setValue(transactionRequestDTO.getValue());
            receivedTransaction.setTransactionType(transactionRequestDTO.getTransactionType());
            receivedTransaction.setNumber(transactionRequestDTO.getNumber());
            receivedTransaction.setAgency(transactionRequestDTO.getAgency());
            receivedTransaction.setAccount(senderAccount);

            destinationAccount.setBalance(destinationAccount.getBalance() + transactionRequestDTO.getValue());

        } else if (senderAccount.getNumber() != destinationAccount.getNumber()) {
            receivedTransaction.setValue(transactionRequestDTO.getValue());
            receivedTransaction.setTransactionType(transactionRequestDTO.getTransactionType());
            receivedTransaction.setNumber(transactionRequestDTO.getNumber());
            receivedTransaction.setAgency(transactionRequestDTO.getAgency());
            receivedTransaction.setAccount(destinationAccount);

            senderAccount.setBalance(senderAccount.getBalance() - transactionRequestDTO.getValue());
            destinationAccount.setBalance(destinationAccount.getBalance() + transactionRequestDTO.getValue());
        }

        TransactionValidator transactionValidator = new TransactionValidator();
        transactionValidator.setSenderAccount(senderAccount);
        transactionValidator.setDestinationAccount(destinationAccount);
        transactionValidator.setTransaction(receivedTransaction);

        return transactionValidator;
    }

    private TransactionValidator withdrawValidator(
            Account senderAccount, Account destinationAccount, TransactionRequestDTO transactionRequestDTO
    ) throws CustomBadRequestException {
        Transaction receivedTransaction = new Transaction();

        if (senderAccount.getNumber() != destinationAccount.getNumber()) {
            throw new CustomBadRequestException("Operação recusada. Você não pode efetuar saque de outra conta!");
        }

        if (senderAccount.getBalance() <= 0) {
            throw new CustomBadRequestException("Saldo insuficiente!");
        }

        if (transactionRequestDTO.getValue() > senderAccount.getBalance()) {
            throw new CustomBadRequestException("Operação recusada. Você não pode sacar um valor acima do seu saldo.");
        }

        receivedTransaction.setValue(transactionRequestDTO.getValue());
        receivedTransaction.setTransactionType(transactionRequestDTO.getTransactionType());
        receivedTransaction.setNumber(transactionRequestDTO.getNumber());
        receivedTransaction.setAgency(transactionRequestDTO.getAgency());
        receivedTransaction.setAccount(senderAccount);

        senderAccount.setBalance(senderAccount.getBalance() - transactionRequestDTO.getValue());

        TransactionValidator transactionValidator = new TransactionValidator();
        transactionValidator.setSenderAccount(senderAccount);
        transactionValidator.setDestinationAccount(destinationAccount);
        transactionValidator.setTransaction(receivedTransaction);

        return transactionValidator;
    }
}
