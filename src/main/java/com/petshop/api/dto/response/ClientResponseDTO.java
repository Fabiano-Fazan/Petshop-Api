package com.petshop.api.dto.response;

import com.petshop.api.model.entities.Address;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {
    private UUID id;
    private String name;
    private String phone;
    private String cpf;
    private Address address;
    private List<AnimalResponseDTO> animals;
}
