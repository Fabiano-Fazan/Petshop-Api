package com.petshop.api.dto.request;

import com.petshop.api.model.enums.VeterinarianCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatedVeterinarianDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "CRMv is required")
    private String crmv;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)?[\\s-]?[9]?[0-9]{4,5}-?[0-9]{4}$",
            message = "Phone number must be in the format (XX) XXXXX-XXXX or (XX) XXXX-XXXX")
    private String phone;

    @NotNull(message = "Veterinarian Category is required")
    private VeterinarianCategory veterinarianCategory;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

}
