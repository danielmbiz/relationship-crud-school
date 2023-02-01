package com.example.relationshipcrudschool.services;

import com.example.relationshipcrudschool.dto.*;
import com.example.relationshipcrudschool.models.Address;
import com.example.relationshipcrudschool.models.ClassRoom;
import com.example.relationshipcrudschool.models.Course;
import com.example.relationshipcrudschool.models.Student;
import com.example.relationshipcrudschool.repositories.AddressRepository;
import com.example.relationshipcrudschool.repositories.ClassRoomRepository;
import com.example.relationshipcrudschool.repositories.CourseRepository;
import com.example.relationshipcrudschool.repositories.StudentRepository;
import com.example.relationshipcrudschool.services.exception.ResourceNotFoundException;
import com.example.relationshipcrudschool.services.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private AddressRepository addressRepository;

    public StudentResponse save(StudentRequest request) {
        Student student = repository.save(createStudent(request, 0L));
        return StudentResponse.builder()
                .name(student.getName())
                .classRoomDTO(ClassRoomDTO.of(student.getClassRoom()))
                .courses(student.getCourses()
                        .stream()
                        .map(CourseDTO::of)
                        .collect(Collectors.toList()))
                .addressDTO(AddressDTO.of(student.getAddress()))
                .build();
    }

    private ClassRoom validateClassRoom(Long classRoomId) {
        return classRoomRepository.findById(classRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("Sala de Aula não encontrada"));
    }

    public StudentRequest findById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudante não encontrado. ID: " + id));
        return StudentRequest.of(student);
    }

    public StudentResponse update(Long id, StudentRequest request) {
        findById(id);
        Student student = repository.save(createStudent(request, id));

        return StudentResponse.builder()
                .name(student.getName())
                .classRoomDTO(ClassRoomDTO.of(student.getClassRoom()))
                .courses(student.getCourses()
                        .stream()
                        .map(CourseDTO::of)
                        .collect(Collectors.toList()))
                .addressDTO(AddressDTO.of(student.getAddress()))
                .build();

    }

    private Student createStudent(StudentRequest request, Long id) {
        ClassRoom classRoom = validateClassRoom(request.getClassRoomId());
        List<Course> listCourse = createListCourse(request.getCourseIds());
        validateAddress(request.getAddressDTO());
        validateCityAndState(request.getAddressDTO().getCity(), request.getAddressDTO().getState());
        Address address = createOrSelectAddress(request.getAddressDTO(), id);
        Student student = Student.of(request, classRoom, listCourse, address);
        if (id > 0) {
            student.setId(id);
        }

        return student;
    }

    private List<Course> createListCourse(List<Long> courseIds) {
        List<Course> listCourse = new ArrayList<>();

        for (Long courseId : courseIds) {
            listCourse.add(
                    courseRepository.findById(courseId)
                            .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"))
            );
        }

        return listCourse;
    }

    private Address createOrSelectAddress(AddressDTO addressDTO, Long id) {
        Address address;
        if (id > 0) {
            validateAddressId(addressDTO.getId());
            address = addressRepository.findById(addressDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado."));
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
        } else {
            address = Address.of(addressDTO);
        }

        return address;
    }

    private void validateAddressId(Long idAddress) {
        if (idAddress == null) {
            throw new ValidationException("Id do endereço é obrigatório na alteração");
        }
    }

    private void validateAddress(AddressDTO addressDTO) {
        if (addressDTO == null) {
            throw new ValidationException("Endereço é obrigatório");
        }
    }

    private void validateCityAndState(String city, String state) {
        if ((city == null) || (city.isEmpty())) {
            throw new ValidationException("Cidade é obrigatório");
        }
        if ((state == null) || (state.isEmpty())) {
            throw new ValidationException("Estado é obrigatório");
        }
    }

    public List<StudentRequest> findAll() {
        return repository.findAll()
                .stream()
                .map(StudentRequest::of)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
