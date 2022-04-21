package com.alanpatrik.hogwarts.modules.clients;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortingHatChoice {

    @JsonProperty("sorting-hat-choice")
    private UUID selectorKey;

}
