package com.alanpatrik.hogwarts.modules.student;

import com.alanpatrik.hogwarts.modules.student.dto.StudentDTO;

import java.util.UUID;

public interface StudentService {

    StudentDTO getById(UUID id);

    Student create(Student student);
}
