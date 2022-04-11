package com.alanpatrik.bancosantander.modules.user;

import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.http.HttpResponseDTO;
import com.alanpatrik.bancosantander.modules.user.dto.UserRequestDTO;
import com.alanpatrik.bancosantander.modules.user.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<UserResponseDTO>> getById(@PathVariable Long id) throws CustomNotFoundException {
        UserResponseDTO user = userService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, user));
    }

    @GetMapping
    public ResponseEntity<HttpResponseDTO<Page<UserResponseDTO>>> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestParam(required = false, defaultValue = "Asc") String sort) {

        Page<UserResponseDTO> userPage = userService.getAll(page, size, sort);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, userPage));
    }

    @GetMapping("/pesquisarNome")
    public ResponseEntity<HttpResponseDTO<Page<UserResponseDTO>>> searchByName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "3") int size,
            @RequestParam(required = false, defaultValue = "Asc") String sort
    ) {

        Page<UserResponseDTO> userPage = userService.searchByName(name, page, size, sort);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, userPage));
    }

    @PostMapping
    public ResponseEntity<HttpResponseDTO<UserResponseDTO>> create(@RequestBody @Valid UserRequestDTO object) throws CustomBadRequestException {
        UserResponseDTO user = userService.create(object);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.CREATED, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<UserResponseDTO>> update(@PathVariable Long id, @RequestBody UserRequestDTO object) throws CustomNotFoundException {
        UserResponseDTO userResponseDTO = userService.update(id, object);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, userResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws CustomNotFoundException {
        userService.delete(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.NO_CONTENT));
    }
}
