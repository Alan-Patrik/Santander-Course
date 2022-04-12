package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.modules.transaction.dto.TransactionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAll(Pageable pageable);

    //    @Query(nativeQuery = true, value = "select * from transacao t where substring(t.data_criacao, 6, 2) = :month")
    @Query(nativeQuery = true, value = "select * from transacao where TO_CHAR(data_criacao, 'MM') = :month")
    Page<Transaction> searchByMonth(@Param("month") String month, Pageable pageable);
}
