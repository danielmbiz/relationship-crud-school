package com.example.relationshipcrudschool.models;

import com.example.relationshipcrudschool.dto.AddressDTO;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Cidade é obrigatória")
    private String city;
    @NotBlank(message = "Estado é obrigatório")
    private String state;
    @OneToOne(mappedBy = "address")
    private Student student;

    public static Address of(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        return address;
    }
}
