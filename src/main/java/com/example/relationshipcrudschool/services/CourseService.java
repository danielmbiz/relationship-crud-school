package com.example.relationshipcrudschool.services;

import com.example.relationshipcrudschool.dto.CourseDTO;
import com.example.relationshipcrudschool.services.exception.ResourceNotFoundException;
import com.example.relationshipcrudschool.models.Course;
import com.example.relationshipcrudschool.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public CourseDTO save(CourseDTO request) {
        Course course = repository.save(Course.of(request));
        return CourseDTO.of(course);
    }

    public CourseDTO findById(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado. ID: " + id));
        return CourseDTO.of(course);
    }

    public CourseDTO update(Long id, CourseDTO request) {
        findById(id);
        Course course = Course.of(request);
        course.setId(id);
        return CourseDTO.of(repository.save(course));
    }

    public List<CourseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(CourseDTO::of)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
