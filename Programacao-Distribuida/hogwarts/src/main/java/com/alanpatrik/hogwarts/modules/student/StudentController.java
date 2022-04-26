package com.alanpatrik.hogwarts.modules.student;

import com.alanpatrik.hogwarts.exceptions.CustomInternalServerException;
import com.alanpatrik.hogwarts.exceptions.CustomNotFoundException;
import com.alanpatrik.hogwarts.http.HttpResponseDTO;
import com.alanpatrik.hogwarts.modules.student.dto.StudentDTO;
import com.alanpatrik.hogwarts.modules.student.dto.StudentRequestDTO;
import com.alanpatrik.hogwarts.modules.student.dto.StudentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/aluno")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<StudentDTO>> getById(@PathVariable UUID id) throws CustomNotFoundException, CustomInternalServerException {
        StudentDTO studentDTO = studentService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(studentDTO));
    }

    @PostMapping
    public ResponseEntity<HttpResponseDTO<StudentResponseDTO>> create(@RequestBody StudentRequestDTO studentRequestDTO) throws CustomInternalServerException {
        StudentResponseDTO obj = studentService.create(studentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(obj));
    }
}
