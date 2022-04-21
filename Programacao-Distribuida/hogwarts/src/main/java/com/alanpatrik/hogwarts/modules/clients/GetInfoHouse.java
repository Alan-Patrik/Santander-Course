package com.alanpatrik.hogwarts.modules.clients;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetInfoHouse {

    public InfoClass execute(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<InfoClass> infoClassResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                InfoClass.class);

        if (infoClassResponseEntity.getStatusCode().isError()) {
            //TODO lançar erro proprio
            return null;
        }

        return infoClassResponseEntity.getBody();
    }
}
