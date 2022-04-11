package com.alanpatrik.bancosantander.modules.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByName(String name, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByCpf(String cpf);
}
