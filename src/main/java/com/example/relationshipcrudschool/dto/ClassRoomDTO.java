package com.example.relationshipcrudschool.dto;

import com.example.relationshipcrudschool.models.ClassRoom;
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
public class ClassRoomDTO {
    private Long id;
    @NotBlank(message = "Sala de Aula é obrigatório")
    private String description;

    public static ClassRoomDTO of(ClassRoom classRoom) {
        ClassRoomDTO classRoomDTO = new ClassRoomDTO();
        BeanUtils.copyProperties(classRoom, classRoomDTO);
        return classRoomDTO;
    }
}
