package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.clients.GetInfoTransaction;
import com.alanpatrik.bancosantander.modules.clients.GetTransactionList;
import com.alanpatrik.bancosantander.modules.clients.dto.TransactionDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionAccountDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final TransactionMapper transactionMapper = TransactionMapper.INSTANCE;
    private final GetInfoTransaction getInfoTransaction;
    private final GetTransactionList getTransactionList;
    private final String URL_POST_TRANSACTION = "http://localhost:8090/transacao";
    private final String URL_GET_TRANSACTION_LIST = "http://localhost:8090/transacao";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<TransactionResponseDTO> getAll() throws CustomInternalServerException, JsonProcessingException {
        List<TransactionDTO> transactionDTOList = getTransactionList.execute(URL_GET_TRANSACTION_LIST);
        TransactionAccountDTO transactionAccountDTO =  new TransactionAccountDTO();

        for (TransactionDTO transactionDTO : transactionDTOList) {
            var account = accountRepository.findById(transactionDTO.getAccountId());

            transactionAccountDTO.setNumber(transactionDTO.getNumber());
            transactionAccountDTO.setAgency(transactionDTO.getAgency());
            transactionAccountDTO.setAccountType(account.get().getAccountType());
            transactionAccountDTO.setUser(userMapper.toUserAccountDTO(account.get().getUser()));

        }

        List<TransactionResponseDTO> transactionResponseDTOList = transactionMapper.toResponseDTO(
                transactionDTOList, transactionAccountDTO);

        return transactionResponseDTOList;
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
