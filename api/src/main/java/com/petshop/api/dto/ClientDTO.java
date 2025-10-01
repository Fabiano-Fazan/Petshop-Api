package com.petshop.api.dto;

import com.petshop.api.model.entities.Address;
import com.petshop.api.model.entities.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private UUID id;
    private String name;
    private String phone;
    private Address address;
    private List<Animal> animals;


}
