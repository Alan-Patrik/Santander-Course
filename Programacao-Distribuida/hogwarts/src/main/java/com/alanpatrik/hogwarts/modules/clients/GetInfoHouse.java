package com.alanpatrik.hogwarts.modules.clients;

import com.alanpatrik.hogwarts.exceptions.CustomInternalServerException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetInfoHouse {

    public HouseInfo execute(String url) throws CustomInternalServerException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<HouseInfo> infoClassResponseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                HouseInfo.class);

        if (infoClassResponseEntity.getStatusCode().isError()) {
            throw new CustomInternalServerException("Erro ao conectar serviço de busca das informações da casa.");
        }

        return infoClassResponseEntity.getBody();
    }
}
