package com.alanpatrik.hogwarts.modules.student;

import com.alanpatrik.hogwarts.modules.clients.InfoClass;
import com.alanpatrik.hogwarts.modules.student.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/aluno")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable UUID id) {
        StudentDTO studentDTO = studentService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentDTO);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student obj = studentService.create(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(obj);
    }
}
