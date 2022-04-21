package com.alanpatrik.hogwarts.modules.clients;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoClass {

    private UUID id;
    private String name;
    private String animal;
    private String founder;

    @OneToMany
    private List<Values> valuesList = new ArrayList<>();

}
