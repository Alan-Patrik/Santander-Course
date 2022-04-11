package com.alanpatrik.bancosantander.modules.user;

import com.alanpatrik.bancosantander.modules.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Campo CPF é obrigatório!")
    @Pattern(regexp = "^\\d{3}.\\d{3}.\\d{3}-\\d{2}$", message = "Por favor, insira um cpf válido! Ex(111.111.111-11)")
    @Column(name = "cpf", unique = true)
    private String cpf;

    @NotNull(message = "Campo SENHA é obrigatório!")
    @Size(min = 4, max = 8, message = "No campo SENHA deve ser no mínimo 4 e no máximo 8 caracteres.")
    @Column(name = "senha")
    private String password;

    @NotNull(message = "Campo NOME é obrigatório!")
    @Column(name = "nome")
    private String name;

    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime descriptionDate;

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    private LocalDateTime updateDate;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}
