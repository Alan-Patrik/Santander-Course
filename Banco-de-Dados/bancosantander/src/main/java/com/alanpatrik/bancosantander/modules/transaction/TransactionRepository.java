package com.alanpatrik.bancosantander.modules.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from transacao t where substring(t.data_criacao, 6, 2) = :month")
    Page<Transaction> searchByMonth(@Param("month") int month, Pageable pageable);
}
