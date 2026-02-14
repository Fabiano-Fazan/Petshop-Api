package com.petshop.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianResponseDto {

    @Schema(description = "Veterinarian ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Schema(description = "Veterinarian name", example = "Dr. House")
    private String name;
    @Schema(description = "CRMV", example = "12345")
    private String crmv;
    @Schema(description = "Phone number", example = "(11) 91234-5678")
    private String phone;
    @Schema(description = "Category name", example = "Surgeon")
    private String category;
    @Schema(description = "Email", example = "dr.house@example.com")
    private String email;
}
