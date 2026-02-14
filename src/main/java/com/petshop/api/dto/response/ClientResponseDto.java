package com.petshop.api.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

    @Schema(description = "Client ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Client name", example = "João Silva")
    private String name;
    @Schema(description = "Phone number", example = "(11) 91234-5678")
    private String phone;
    @Schema(description = "CPF", example = "123.456.789-00")
    private String cpf;
    @Schema(description = "Address")
    private AddressResponseDto address;
    @Schema(description = "List of animals")
    private List<AnimalResponseDto> animals;
}
