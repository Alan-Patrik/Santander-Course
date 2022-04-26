package com.alanpatrik.hogwarts.modules.clients;

import com.alanpatrik.hogwarts.exceptions.CustomInternalServerException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetSelectorHouseKey {

    private final String URL = "https://api-harrypotter.herokuapp.com/sortinghat";

    public SortingHatChoice execute() throws CustomInternalServerException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<SortingHatChoice> sortingHatChoiceResponseEntity = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                entity,
                SortingHatChoice.class);

        if (sortingHatChoiceResponseEntity.getStatusCode().isError()) {
            throw new CustomInternalServerException("Erro ao conectar serviço de busca da chave da casa.");
        }

        return sortingHatChoiceResponseEntity.getBody();
    }
}
