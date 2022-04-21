package com.alanpatrik.hogwarts.modules.clients;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetSelectorHouseKey {

    private final String URL = "https://api-harrypotter.herokuapp.com/sortinghat";

    public SortingHatChoice execute() {
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
            //TODO lançar erro proprio
            return null;
        }

        return sortingHatChoiceResponseEntity.getBody();
    }
}
