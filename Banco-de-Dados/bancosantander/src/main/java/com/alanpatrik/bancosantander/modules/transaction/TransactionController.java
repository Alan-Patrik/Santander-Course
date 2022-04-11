package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.http.HttpResponseDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionRequestDTO;
import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transacao")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("/pesquisarMes")
    public ResponseEntity<HttpResponseDTO<Page<TransactionResponseDTO>>> searchByMonth(
            @RequestParam int month,
            @RequestParam int page,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestParam(required = false, defaultValue = "Asc") String sort
    ) throws CustomBadRequestException {
        Page<TransactionResponseDTO> transactionResponseDTOPage = transactionService.searchByMonth(month, page, size, sort);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, transactionResponseDTOPage));
    }

    @GetMapping
    public ResponseEntity<HttpResponseDTO<Page<TransactionResponseDTO>>> getAll(
            @RequestParam int page,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestParam(required = false, defaultValue = "Asc") String sort) {
        Page<TransactionResponseDTO> transactionResponseDTOPage = transactionService.getAll(page, size, sort);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, transactionResponseDTOPage));
    }

    @PostMapping
    public ResponseEntity<HttpResponseDTO<TransactionResponseDTO>> create(
            @RequestBody TransactionRequestDTO transactionRequestDTO
    ) throws CustomNotFoundException, CustomBadRequestException {
        TransactionResponseDTO receivedTransactionResponseDTO = transactionService.create(transactionRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, receivedTransactionResponseDTO));
    }
}
