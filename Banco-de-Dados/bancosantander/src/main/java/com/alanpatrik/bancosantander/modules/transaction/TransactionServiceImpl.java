package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.clients.GetInfoTransaction;
import com.alanpatrik.bancosantander.modules.clients.dto.TransactionDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionAccountDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import com.alanpatrik.bancosantander.modules.user.dto.UserAccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final TransactionMapper transactionMapper = TransactionMapper.INSTANCE;
    private final GetInfoTransaction getInfoTransaction;
    private final String URL_POST_TRANSACTION = "http://localhost:8090/transacao";

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
    public TransactionResponseDTO create(TransactionRequestDTO transactionRequestDTO)
            throws CustomNotFoundException, CustomBadRequestException, CustomInternalServerException {
        TransactionDTO transactionDTO = getInfoTransaction.execute(URL_POST_TRANSACTION, transactionRequestDTO);

        var senderAccount = accountRepository.findByNumber(transactionDTO.getNumber());
        var destinationAccount = accountRepository.findById(transactionDTO.getAccountId());

        switch (transactionDTO.getTransactionType()) {
            case TRANSFERENCIA:
                senderAccount.get().setBalance(senderAccount.get().getBalance() - transactionDTO.getValue());
                destinationAccount.get().setBalance(destinationAccount.get().getBalance() + transactionDTO.getValue());
                accountRepository.save(senderAccount.get());
                accountRepository.save(destinationAccount.get());
                break;

            case DEPOSITO:
                destinationAccount.get().setBalance(destinationAccount.get().getBalance() + transactionDTO.getValue());
                accountRepository.save(destinationAccount.get());
                break;

            case SAQUE:
                destinationAccount.get().setBalance(destinationAccount.get().getBalance() - transactionDTO.getValue());
                accountRepository.save(destinationAccount.get());
                break;

            default:
                throw new CustomBadRequestException("Essa transação não existe!");
        }

        TransactionAccountDTO transactionAccountDTO =  new TransactionAccountDTO();
        transactionAccountDTO.setNumber(transactionDTO.getNumber());
        transactionAccountDTO.setAgency(transactionDTO.getAgency());
        transactionAccountDTO.setAccountType(destinationAccount.get().getAccountType());
        transactionAccountDTO.setUser(userMapper.toUserAccountDTO(destinationAccount.get().getUser()));

        TransactionResponseDTO transactionResponseDTO = transactionMapper
                .fromTransactionDTOToTransactionResponseDTO(transactionDTO, transactionAccountDTO);

        return transactionResponseDTO;
    }
}
