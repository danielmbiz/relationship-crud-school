package com.example.relationshipcrudschool.models;

import com.example.relationshipcrudschool.dto.ClassRoomDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Sala de Aula é obrigatório")
    private String description;

    public static ClassRoom of(ClassRoomDTO classRoomDTO) {
        ClassRoom classRoom = new ClassRoom();
        BeanUtils.copyProperties(classRoomDTO, classRoom);
        return classRoom;
    }

}
