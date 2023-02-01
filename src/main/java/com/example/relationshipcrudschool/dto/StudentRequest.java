package com.example.relationshipcrudschool.dto;

import com.example.relationshipcrudschool.models.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentRequest {
    @NotBlank(message = "Nome é obrigatório!")
    private String name;
    private Long classRoomId;
    private List<Long> courseIds;
    @JsonProperty("address")
    private AddressDTO addressDTO;

    public static StudentRequest of(Student student) {
        StudentRequest studentDTO = new StudentRequest();
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }
}
