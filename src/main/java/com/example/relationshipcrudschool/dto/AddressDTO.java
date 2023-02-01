package com.example.relationshipcrudschool.dto;

import com.example.relationshipcrudschool.models.Address;
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
public class AddressDTO {
    private Long id;
    @NotBlank(message = "Cidade é obrigatória")
    private String city;
    @NotBlank(message = "Estado é obrigatório")
    private String state;

    public static AddressDTO of(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, addressDTO);
        return addressDTO;
    }
}
