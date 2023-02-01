package com.example.relationshipcrudschool.dto;

import com.example.relationshipcrudschool.models.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private Long id;
    @NotBlank(message = "Descrição é obrigatório")
    private String description;

    public static CourseDTO of(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(course, courseDTO);
        return courseDTO;
    }
}
