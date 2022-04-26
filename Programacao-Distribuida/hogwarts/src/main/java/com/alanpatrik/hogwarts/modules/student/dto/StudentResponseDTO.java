package com.alanpatrik.hogwarts.modules.student.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class StudentResponseDTO {

    private UUID id;
    private String name;
    private String series;
    private String homeKey;
}
