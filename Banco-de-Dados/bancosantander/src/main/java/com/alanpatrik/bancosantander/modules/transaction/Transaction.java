package com.alanpatrik.bancosantander.modules.transaction;

import com.alanpatrik.bancosantander.enums.TransactionType;
import com.alanpatrik.bancosantander.modules.account.Account;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transacao")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo VALOR é obrigatório!")
    @Column(name = "valor")
    private double value;

    @NotNull(message = "Campo TIPO TRANSAÇÃO é obrigatório!")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transacao")
    private TransactionType transactionType;

    @NotNull(message = "Campo NÚMERO é obrigatório!")
    @Min(value = 2, message = "No campo NÚMERO deve ser no mínimo 2 caracteres.")
//    @Max(value = 9, message = "No máximo 9 caracteres.")
    @Column(name = "numero")
    private int number;

    @NotNull(message = "Campo AGÊNCIA é obrigatório!")
    @Min(value = 2, message = "No campo AGÊNCIA deve ser no mínimo 2 caracteres.")
//    @Max(value = 4, message = "No máximo 4 caracteres.")
    @Column(name = "agencia")
    private int agency;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime descriptionDate;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Account account;
}
