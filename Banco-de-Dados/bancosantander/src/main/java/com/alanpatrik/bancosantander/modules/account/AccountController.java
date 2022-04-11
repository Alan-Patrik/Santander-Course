package com.alanpatrik.bancosantander.modules.account;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.http.HttpResponseDTO;
import com.alanpatrik.bancosantander.modules.account.dto.AccountRequestDTO;
import com.alanpatrik.bancosantander.modules.account.dto.AccountResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contas")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<AccountResponseDTO>> getById(@PathVariable Long id) throws CustomNotFoundException {
        AccountResponseDTO accountResponseDTO = accountService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, accountResponseDTO));
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<HttpResponseDTO<AccountResponseDTO>> getByNumber(@RequestParam(name = "numero") Integer number) throws CustomNotFoundException, CustomBadRequestException {
        AccountResponseDTO accountResponseDTO = accountService.getByNumber(number);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, accountResponseDTO));
    }

    @GetMapping
    public ResponseEntity<HttpResponseDTO<Page<AccountResponseDTO>>> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestParam(required = false, defaultValue = "Asc") String sort) {
        Page<AccountResponseDTO> accountResponseDTOPage = accountService.getAll(page, size, sort);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, accountResponseDTOPage));
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<AccountResponseDTO>> create(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) throws CustomNotFoundException, CustomBadRequestException {
        AccountResponseDTO accountResponseDTO = accountService.create(id, accountRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.CREATED, accountResponseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<AccountResponseDTO>> update(@PathVariable Long id, @RequestBody AccountRequestDTO accountRequestDTO) throws CustomNotFoundException {
        AccountResponseDTO accountResponseDTO = accountService.update(id, accountRequestDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, accountResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws CustomNotFoundException {
        accountService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.NO_CONTENT));

    }
}
