package com.alanpatrik.bancosantander.modules.account;

import com.alanpatrik.bancosantander.enums.AccountType;
import com.alanpatrik.bancosantander.modules.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "conta")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo NÚMERO é obrigatório!")
    @Min(value = 2, message = "No campo NÚMERO deve ser no mínimo 2 caracteres.")
//    @Max(value = 9, message = "No máximo 9 caracteres.")
    @Column(name = "numero")
    private Integer number;

    @NotNull(message = "Campo AGÊNCIA é obrigatório!")
    @Min(value = 2, message = "No campo AGÊNCIA deve ser no mínimo 2 caracteres.")
//    @Max(value = 4, message = "No máximo 4 caracteres.")
    @Column(name = "agencia")
    private Integer agency;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime descriptionDate;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime updateDate;

    @NotNull(message = "Campo SALDO é obrigatório!")
    @Column(name = "saldo")
    private double balance;

    @NotNull(message = "Campo TIPO CONTA é obrigatório!")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta")
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private User user;
}
