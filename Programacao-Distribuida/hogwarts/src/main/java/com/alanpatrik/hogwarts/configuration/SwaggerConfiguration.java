package com.alanpatrik.hogwarts.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Hogwarts School")
                                .description("Aplicação feita para consumir dois endpoints de um microserviço.")
                                .version("v0.0.1")
                                .license(
                                        new License().name("Apache 2.0").url("http://springdoc.org")
                                ).contact(
                                        new Contact()
                                                .name("Alan Patrik")
                                                .email("alanpatrik-fragozo@gmail.com")
                                                .url("https://github.com/Alan-Patrik")
                                )
                );
    }
}
