package com.alanpatrik.hogwarts.modules.student.dto;

import com.alanpatrik.hogwarts.modules.clients.InfoClass;
import lombok.Data;

@Data
public class StudentDTO {

    private String name;
    private String series;
    private InfoClass infoClass;
}
