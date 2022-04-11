package com.alanpatrik.bancosantander.repositories;

import com.alanpatrik.bancosantander.exceptions.CustomNotFoundException;
import com.alanpatrik.bancosantander.modules.user.User;
import com.alanpatrik.bancosantander.modules.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void validar_findAll_vazio_se_repository_em_branco() {
        var user = userRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), user);
    }

    @Test
    public void trazer_usuarios_cadastrados_no_findAll() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("123456");

        User user1 = new User();
        user1.setName("Larissa Bastos");
        user1.setCpf("222.222.222-22");
        user1.setPassword("654321");

        entityManager.persist(user);
        entityManager.persist(user1);

        var users = userRepository.findAll();

        Assertions.assertNotNull(users);
    }

    @Test
    public void campo_de_senha_invalida() {
        User user = new User();

        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            user.setName("Alan Patrik");
            user.setCpf("111.111.111-11");
            user.setPassword("1");

            userRepository.save(user);
        });

        String expectedMessage = "No campo SENHA deve ser no mínimo 4 e no máximo 8 caracteres.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void trazer_usuario_pelo_nome_com_sucesso() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        entityManager.persist(user);

        var returnedUser = userRepository.findById(user.getId()).get();

        Assertions.assertEquals("Alan Patrik", returnedUser.getName());
    }


    @Test
    public void atualizando_usuario_e_retornando_atualizado() {
        User user = new User();
        user.setName("Anderson");
        user.setCpf("222.222.222-22");
        user.setPassword("222222");

        entityManager.persist(user);

        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        var updatedUser = userRepository.findById(user.getId()).get();

        Assertions.assertEquals(updatedUser.getId(), user.getId());
        Assertions.assertEquals("Alan Patrik", user.getName());
        Assertions.assertFalse(!updatedUser.equals(user));
    }

    @Test
    public void deletando_usuario() {
        User user = new User();
        user.setName("Alan Patrik");
        user.setCpf("111.111.111-11");
        user.setPassword("111111");

        user = userRepository.save(user);
        userRepository.delete(user);

        var userList = userRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), userList);
    }
}
