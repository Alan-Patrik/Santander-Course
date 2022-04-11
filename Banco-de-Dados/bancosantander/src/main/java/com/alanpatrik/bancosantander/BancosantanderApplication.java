package com.alanpatrik.bancosantander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BancosantanderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancosantanderApplication.class, args);
    }

}
