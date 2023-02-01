package com.example.relationshipcrudschool.repositories;

import com.example.relationshipcrudschool.models.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
