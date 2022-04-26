package com.alanpatrik.hogwarts.modules.student.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestDTO {

    private String name;
    private String series;
    private String homeKey;
}
