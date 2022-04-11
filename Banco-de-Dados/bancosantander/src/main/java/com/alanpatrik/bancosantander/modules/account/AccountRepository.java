package com.alanpatrik.bancosantander.modules.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNumber(Integer number);

    Page<Account> findAll(Pageable pageable);
}
