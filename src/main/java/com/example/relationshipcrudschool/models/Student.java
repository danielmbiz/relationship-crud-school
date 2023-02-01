package com.example.relationshipcrudschool.models;

import com.example.relationshipcrudschool.dto.StudentRequest;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "class_room_id", nullable = false)
    private ClassRoom classRoom;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Course> courses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    public static Student of(StudentRequest studentDTO, ClassRoom classRoom, List<Course> courseList, Address address) {
        return Student.builder()
                .name(studentDTO.getName())
                .classRoom(classRoom)
                .courses(courseList)
                .address(address)
                .build();
    }

}
