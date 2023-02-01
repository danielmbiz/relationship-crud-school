package com.example.relationshipcrudschool.services;

import com.example.relationshipcrudschool.dto.ClassRoomDTO;
import com.example.relationshipcrudschool.services.exception.ResourceNotFoundException;
import com.example.relationshipcrudschool.models.ClassRoom;
import com.example.relationshipcrudschool.repositories.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassRoomService {

    @Autowired
    private ClassRoomRepository repository;

    public ClassRoomDTO save(ClassRoomDTO request) {
        ClassRoom classRoom = repository.save(ClassRoom.of(request));
        return ClassRoomDTO.of(classRoom);
    }

    public ClassRoomDTO findById(Long id) {
        ClassRoom classRoom = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala de Aula não encontrada. ID: " + id));
        return ClassRoomDTO.of(classRoom);
    }

    public ClassRoomDTO update(Long id, ClassRoomDTO request) {
        findById(id);
        ClassRoom classRoom = ClassRoom.of(request);
        classRoom.setId(id);
        return ClassRoomDTO.of(repository.save(classRoom));
    }

    public List<ClassRoomDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(ClassRoomDTO::of)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
