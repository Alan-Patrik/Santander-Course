package com.alanpatrik.bancosantander.services;

import com.alanpatrik.bancosantander.dto.UserRequestBuilderDTO;
import com.alanpatrik.bancosantander.dto.UserResponseBuilderDTO;
import com.alanpatrik.bancosantander.exceptions.CustomBadRequestException;
import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.user.UserServiceImpl;
import com.alanpatrik.bancosantander.modules.user.dto.UserRequestDTO;
import com.alanpatrik.bancosantander.modules.user.dto.UserResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void retornando_todos_usuarios_cadastrados_deve_passar() throws Exception {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();
        userService.create(userRequestDTO);

        var expected = userService.getAll(0, 5, "Asc");

        Assertions.assertNotNull(expected);
    }

    @Test
    public void verificando_se_usuario_existe_deve_passar() throws CustomNotFoundException, CustomBadRequestException {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();
        var registeredUser = userService.create(userRequestDTO);
        var expectedResponse = userService.verifyIfUserExists(registeredUser.getId());

        Assertions.assertEquals(expectedResponse.getId(), registeredUser.getId());
    }

    @Test
    public void retornando_usuario_que_nao_existe_nao_deve_passar() {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            userService.verifyIfUserExists(1L);
        });

        String expectedMessage = "Usuario com o id 1 não foi encontrado.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void verificando_se_usuario_existe_por_id_deve_passar() throws CustomNotFoundException, CustomBadRequestException {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();
        var registeredUser = userService.create(userRequestDTO);
        var expectedResponse = userService.getById(registeredUser.getId());

        Assertions.assertEquals(expectedResponse.getId(), registeredUser.getId());
    }

    @Test
    public void verificando_se_usuario_existe_por_id_nao_deve_passar() {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            userService.verifyIfUserExists(1L);
        });

        String expectedMessage = "Usuario com o id 1 não foi encontrado.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void pesquisando_usuario_pelo_nome_deve_passar() throws Exception {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();
        userService.create(userRequestDTO);

        var expectedResponse = userService.searchByName("João Carlinhos", 0, 5, "Asc");

        Assertions.assertNotNull(expectedResponse.getContent());
        Assertions.assertEquals("João Carlinhos", expectedResponse.getContent().get(0).getName());

    }

    @Test
    public void pesquisando_usuario_pelo_nome_que_nao_existe_nao_deve_passar() throws Exception {
        var expectedResponse = userService.searchByName("João Carlinhos", 0, 5, "Asc");

        Assertions.assertNotNull(expectedResponse.getContent());
        Assertions.assertEquals(new ArrayList<>(), expectedResponse.getContent());

    }

    @Test
    public void cadastrando_usuario_valido_deve_passar() throws CustomBadRequestException {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();
        UserResponseDTO userResponseDTO = UserResponseBuilderDTO.builder().build().buildResponseDTO();

        var expected = userService.create(userRequestDTO);

        Assertions.assertEquals(expected.getName(), userResponseDTO.getName());
    }

    @Test
    public void cadastrando_usuario_invalido_nao_deve_passar() throws CustomBadRequestException {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            UserRequestDTO userRequestDTO = new UserRequestDTO("Alan", "111.111.111-11", "");
            userService.create(userRequestDTO);
        });

        String expectedMessage = "No campo SENHA deve ser no mínimo 4 e no máximo 8 caracteres.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void atualizando_usuario_deve_passar() throws Exception {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();
        userService.create(userRequestDTO);

        userRequestDTO.setName("Alan");
        userRequestDTO.setCpf("222.222.222-22");
        userRequestDTO.setPassword("222222");

        var expectedResponse = userService.update(1L, userRequestDTO);

        Assertions.assertNotNull(expectedResponse);
        Assertions.assertEquals("Alan", expectedResponse.getName());
    }

    @Test
    public void atualizando_usuario_que_nao_existe_nao_deve_passar() throws Exception {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();

        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            userService.update(1L, userRequestDTO);
        });

        String expectedMessage = "Usuario com o id 1 não foi encontrado.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deletando_usuario_por_id_deve_passar() throws Exception {
        UserRequestDTO userRequestDTO = UserRequestBuilderDTO.builder().build().buildRequestDTO();
        userService.create(userRequestDTO);
        userService.delete(1L);

        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            userService.verifyIfUserExists(1L);
        });

        String expectedMessage = "Usuario com o id 1 não foi encontrado.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void deletando_usuario_por_id_que_nao_existe_nao_deve_passar() throws Exception {
        Exception exception = assertThrows(CustomNotFoundException.class, () -> {
            userService.delete(1L);
        });

        String expectedMessage = "Usuario com o id 1 não foi encontrado.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
