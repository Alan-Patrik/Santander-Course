package com.alanpatrik.hogwarts.modules.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Aluno")
public class Student {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "Campo name é obrigatório!")
    @Column(name = "nome")
    private String name;

    @NotNull(message = "Campo serie é obrigatório!")
    @Column(name = "serie")
    private String series;

    @Column(name = "chave_casa")
    private String homeKey;
}
