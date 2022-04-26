package com.alanpatrik.hogwarts.modules.student;

import com.alanpatrik.hogwarts.exceptions.CustomInternalServerException;
import com.alanpatrik.hogwarts.exceptions.CustomNotFoundException;
import com.alanpatrik.hogwarts.modules.student.dto.StudentDTO;
import com.alanpatrik.hogwarts.modules.student.dto.StudentRequestDTO;
import com.alanpatrik.hogwarts.modules.student.dto.StudentResponseDTO;

import java.util.UUID;

public interface StudentService {

    StudentDTO getById(UUID id) throws CustomNotFoundException, CustomInternalServerException;

    StudentResponseDTO create(StudentRequestDTO studentRequestDTO) throws CustomInternalServerException;
}
