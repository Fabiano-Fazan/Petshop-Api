package com.petshop.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class CreateClientDto {

    @Schema(description = "Client name", example = "João Silva")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Phone number", example = "(11) 91234-5678")
    @Pattern(
            regexp = "^\\(?\\d{2}\\)?[\\s-]?9?\\d{4}-?\\d{4}$",
            message = "Phone number must be in the format (XX) XXXXX-XXXX or (XX) XXXX-XXXX"
    )
    private String phone;

    @Schema(description = "CPF", example = "042.215.610-84")
    @CPF(message = "CPF is invalid")
    @NotBlank(message = "CPF is required")
    private String cpf;

    @Schema(description = "Address")
    @Valid
    @NotNull(message = "Address is required")
    private CreateAddressDto address;
}
