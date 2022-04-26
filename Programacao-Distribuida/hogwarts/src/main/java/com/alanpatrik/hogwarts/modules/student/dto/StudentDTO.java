package com.alanpatrik.hogwarts.modules.student.dto;

import com.alanpatrik.hogwarts.modules.clients.HouseInfo;
import lombok.Data;

@Data
public class StudentDTO {

    private String name;
    private String series;
    private HouseInfo houseInfo;
}
