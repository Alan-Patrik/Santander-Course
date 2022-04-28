package com.alanpatrik.bancosantander.modules.clients;

import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.modules.clients.dto.TransactionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GetTransactionList {

    public List<TransactionDTO> execute(String url) throws CustomInternalServerException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<TransactionDTO>> transactionListResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<TransactionDTO>>() {
                });

        var transactionDTOList = transactionListResponseEntity.getBody();

        if (transactionListResponseEntity.getStatusCode().isError()) {
            throw new CustomInternalServerException("Erro ao retornar Lista de transações.");
        }

        return transactionDTOList;
    }
}