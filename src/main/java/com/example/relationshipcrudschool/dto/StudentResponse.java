package com.example.relationshipcrudschool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("classRoom")
    private ClassRoomDTO classRoomDTO;
    @JsonProperty("courses")
    private List<CourseDTO> courses;
    @JsonProperty("adress")
    private AddressDTO addressDTO;
}
