package com.example.relationshipcrudschool.repositories;

import com.example.relationshipcrudschool.models.Address;
import com.example.relationshipcrudschool.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
