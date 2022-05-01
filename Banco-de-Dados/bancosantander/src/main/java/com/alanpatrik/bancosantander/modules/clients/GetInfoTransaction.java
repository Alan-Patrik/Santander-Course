package com.alanpatrik.bancosantander.modules.clients;

import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.modules.clients.dto.TransactionDTOList;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetInfoTransaction {

    public TransactionDTOList execute(String url, TransactionRequestDTO transactionRequestDTO) throws CustomInternalServerException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(transactionRequestDTO);

        ResponseEntity<TransactionDTOList> infoTransactionResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                TransactionDTOList.class);

        if (infoTransactionResponseEntity.getStatusCode().isError()) {
            throw new CustomInternalServerException("Erro ao conectar serviço de transação.");
        }

        return infoTransactionResponseEntity.getBody();
    }
}
