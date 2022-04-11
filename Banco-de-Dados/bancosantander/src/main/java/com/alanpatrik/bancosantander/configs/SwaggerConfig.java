package com.alanpatrik.bancosantander.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springBancoAPI() {
        return new OpenAPI()
                .info(new Info().title("Banco API")
                        .description("Projeto criado para entrega do modulo de banco de dados")
                        .version("v0.0.1")
                        .contact(
                                new Contact()
                                        .email("alanpatrik.fragozo@gmail.com")
                                        .url("https://www.linkedin.com/in/alan-patrik-fragozo-dos-santos-461b8a1b5/")
                        )
                );
    }
}
