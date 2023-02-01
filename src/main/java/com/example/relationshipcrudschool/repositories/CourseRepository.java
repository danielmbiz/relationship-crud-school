package com.example.relationshipcrudschool.repositories;

import com.example.relationshipcrudschool.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
