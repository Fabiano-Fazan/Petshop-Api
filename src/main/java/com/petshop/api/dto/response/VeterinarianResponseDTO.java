package com.petshop.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianResponseDTO {
    private UUID id;
    private String name;
    private String crmv;
    private String phone;
    private String veterinarianCategory;
    private String email;
}
