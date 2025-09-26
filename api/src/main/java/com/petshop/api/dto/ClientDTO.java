package com.petshop.api.dto;

import com.petshop.api.model.entities.Adress;
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
    private Adress adress;
    private List<AnimalDTO> animals;


}
