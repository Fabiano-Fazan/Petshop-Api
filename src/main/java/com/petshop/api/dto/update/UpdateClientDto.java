package com.petshop.api.dto.update;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientDto {

    @Schema(description = "Client name", example = "João Silva")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @Schema(description = "Phone number", example = "(11) 91234-5678")
    @Pattern(
            regexp = "^\\(?\\d{2}\\)?[\\s-]?9?\\d{4}-?\\d{4}$",
            message = "Phone number must be in the format (XX) XXXXX-XXXX or (XX) XXXX-XXXX"
    )
    private String phone;

    @Schema(description = "Address")
    @Valid
    private UpdateAddressDto address;

}
