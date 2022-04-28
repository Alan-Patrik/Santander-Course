package com.alanpatrik.hogwarts.modules.student;

import com.alanpatrik.hogwarts.exceptions.CustomInternalServerException;
import com.alanpatrik.hogwarts.exceptions.CustomNotFoundException;
import com.alanpatrik.hogwarts.modules.clients.GetInfoHouse;
import com.alanpatrik.hogwarts.modules.clients.GetSelectorHouseKey;
import com.alanpatrik.hogwarts.modules.clients.HouseInfo;
import com.alanpatrik.hogwarts.modules.student.dto.StudentDTO;
import com.alanpatrik.hogwarts.modules.student.dto.StudentRequestDTO;
import com.alanpatrik.hogwarts.modules.student.dto.StudentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GetInfoHouse getInfoHouse;
    private final GetSelectorHouseKey getSelectorHouseKey;
    private final String URL = "https://api-harrypotter.herokuapp.com/house/";

    private final StudentMapper studentMapper = StudentMapper.INSTANCE;

    @Override
    public StudentDTO getById(UUID id) throws CustomNotFoundException, CustomInternalServerException {
        Student student = studentRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(
                String.format("Estudante com o id %s, não foi encontrado.", id)
        ));

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setSeries(student.getSeries());

        if (getInfoHouse.execute(URL + student.getHomeKey()).equals(null)) {
            throw new CustomInternalServerException("A chave da casa retornou vazia");
        }

        HouseInfo houseInfo = getInfoHouse.execute(URL + student.getHomeKey());

        studentDTO.setHouseInfo(houseInfo);

        return studentDTO;
    }

    @Override
    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) throws CustomInternalServerException {
        Student obj = new Student();
        obj.setName(studentRequestDTO.getName());
        obj.setSeries(studentRequestDTO.getSeries());

        var selectorKey = getSelectorHouseKey.execute().getSelectorKey();

        if (selectorKey.equals(null)) {
            throw new CustomInternalServerException("As informações da casa retornaram vazias.");
        }

        obj.setHomeKey(selectorKey.toString());

        obj = studentRepository.save(obj);

        return studentMapper.toDTO(obj);
    }
}
