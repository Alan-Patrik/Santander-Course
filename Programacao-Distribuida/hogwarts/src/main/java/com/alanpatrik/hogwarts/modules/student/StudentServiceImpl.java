package com.alanpatrik.hogwarts.modules.student;

import com.alanpatrik.hogwarts.modules.clients.GetInfoHouse;
import com.alanpatrik.hogwarts.modules.clients.GetSelectorHouseKey;
import com.alanpatrik.hogwarts.modules.clients.InfoClass;
import com.alanpatrik.hogwarts.modules.student.dto.StudentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GetInfoHouse getInfoHouse;
    private final GetSelectorHouseKey getSelectorHouseKey;

    private final String URL = "https://api-harrypotter.herokuapp.com/house/";

    @Override
    public StudentDTO getById(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setSeries(student.getSeries());

        if (getInfoHouse.execute(URL + student.getHomeKey()).equals(null)) {
            throw new RuntimeException("Não retornou 200");
        }

        InfoClass infoClass = getInfoHouse.execute(URL + student.getHomeKey());

        studentDTO.setInfoClass(infoClass);
        System.out.println("cbvcbfcgfcgfcgfc" + infoClass.getValuesList().get(0));

        return studentDTO;
    }

    @Override
    public Student create(Student student) {
        Student obj = new Student();
        obj.setName(student.getName());
        obj.setSeries(student.getSeries());

        if (getSelectorHouseKey.execute().getSelectorKey().equals(null)) {
            throw new RuntimeException("Não retornou 200");
        }

        UUID selectorKey = getSelectorHouseKey.execute().getSelectorKey();
        obj.setHomeKey(selectorKey.toString());

        studentRepository.save(obj);

        return obj;
    }
}
