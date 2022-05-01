package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomInternalServerException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.http.HttpResponseDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transacao")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping
    public ResponseEntity<HttpResponseDTO<List<TransactionResponseDTO>>> getAll() throws CustomInternalServerException, JsonProcessingException {
        List<TransactionResponseDTO> transactionListResponseDTO = transactionService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, transactionListResponseDTO));
    }

    @PostMapping
    public ResponseEntity<HttpResponseDTO<String>> create(
            @RequestBody TransactionRequestDTO transactionRequestDTO
    ) throws CustomNotFoundException {
        transactionService.create("EfetuarTransacao", transactionRequestDTO);

        String response = transactionRequestDTO.getTransactionType().toString() + " efetuada com sucesso!";

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, response));
    }
}
