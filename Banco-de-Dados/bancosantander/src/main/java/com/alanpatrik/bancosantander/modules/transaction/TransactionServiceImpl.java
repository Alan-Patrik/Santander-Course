package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.account.Account;
import com.alanpatrik.bancosantander.modules.account.AccountRepository;
import com.alanpatrik.bancosantander.modules.account.AccountServiceImpl;
import com.alanpatrik.bancosantander.modules.account.dto.AccountRequestDTO;
import com.alanpatrik.bancosantander.modules.clients.GetTransactionList;
import com.alanpatrik.bancosantander.modules.clients.dto.TransactionDTOList;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionAccountDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.alanpatrik.bancosantander.modules.user.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl {

    private final KafkaTemplate<Object, TransactionDTO> kafkaTemplate;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final TransactionMapper transactionMapper = TransactionMapper.INSTANCE;
    private final GetTransactionList getTransactionList;
    private final String URL_GET_TRANSACTION_LIST = "http://localhost:8090/transacao";
    private final AccountServiceImpl accountService;
    private final AccountRepository accountRepository;

    public List<TransactionResponseDTO> getAll() throws CustomInternalServerException, JsonProcessingException {
        List<TransactionDTOList> transactionDTOList = getTransactionList.execute(URL_GET_TRANSACTION_LIST);
        TransactionAccountDTO transactionAccountDTO = new TransactionAccountDTO();

        for (TransactionDTOList transactionList : transactionDTOList) {
            var account = accountRepository.findById(transactionList.getDestinationAccount().getId());

            transactionAccountDTO.setNumber(account.get().getNumber());
            transactionAccountDTO.setAgency(account.get().getAgency());
            transactionAccountDTO.setAccountType(account.get().getAccountType());
            transactionAccountDTO.setUser(userMapper.toUserAccountDTO(account.get().getUser()));
        }

        List<TransactionResponseDTO> transactionResponseDTOList = transactionMapper.toResponseDTOList(
                transactionDTOList, transactionAccountDTO);

        return transactionResponseDTOList;
    }

    public void create(String topic, TransactionRequestDTO transactionRequestDTO) throws CustomNotFoundException {
        Account senderAccount = accountRepository.findByNumber(
                transactionRequestDTO.getNumber()
        ).orElseThrow(() -> new CustomNotFoundException(
                String.format("Conta com o numero %s, não foi encontrada", transactionRequestDTO.getNumber())));

        Account destinationAccount = accountRepository.findById(
                transactionRequestDTO.getAccountId()
        ).orElseThrow(() -> new CustomNotFoundException(
                String.format("Conta com o id %s, não foi encontrada", transactionRequestDTO.getAccountId())));

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setValue(transactionRequestDTO.getValue());
        transactionDTO.setTransactionType(transactionRequestDTO.getTransactionType());
        transactionDTO.setSenderAccount(senderAccount);
        transactionDTO.setDestinationAccount(destinationAccount);

        kafkaTemplate.send(topic, transactionDTO);
    }

    @KafkaListener(topics = "SalvarTransacao", groupId = "MicroServicoSalvarTransacao")
    private void execute(ConsumerRecord<String, TransactionDTO> consumerRecord) throws CustomBadRequestException, CustomNotFoundException {

        log.info("Resposta transação => {}", consumerRecord.value());

        TransactionDTO transactionDTO = consumerRecord.value();

        AccountRequestDTO senderAccount = new AccountRequestDTO();
        senderAccount.setBalance(transactionDTO.getSenderAccount().getBalance());

        AccountRequestDTO destinationAccount = new AccountRequestDTO();
        destinationAccount.setBalance(transactionDTO.getDestinationAccount().getBalance());

        accountService.update(transactionDTO.getSenderAccount().getId(), senderAccount);
        accountService.update(transactionDTO.getDestinationAccount().getId(), destinationAccount);
    }
}
