package com.alanpatrik.hogwarts.modules.student;

import com.alanpatrik.hogwarts.modules.student.dto.StudentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    default StudentResponseDTO toDTO(Student student) {
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(student.getId());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setSeries(student.getSeries());
        studentResponseDTO.setHomeKey(student.getHomeKey());

        return studentResponseDTO;
    }
}
