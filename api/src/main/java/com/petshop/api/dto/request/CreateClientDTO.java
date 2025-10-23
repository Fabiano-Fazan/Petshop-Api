package com.petshop.api.dto.request;

import com.petshop.api.dto.response.AnimalDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CreateClientDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)?[\\s-]?[9]?[0-9]{4,5}-?[0-9]{4}$",
            message = "Phone number must be in the format (XX) XXXXX-XXXX or (XX) XXXX-XXXX")
    private String phone;

    private List<AnimalDTO> animals;

    @Valid
    @NotNull(message = "Adress is required")
     private AddressData address;

    @Getter
    @Setter
    public static class AddressData {
        @NotBlank(message = "Street is required")
        private String street;

        @NotBlank(message = "City is required")
        private String city;

        @NotBlank(message = "State is required")
        @Size(min = 2, max = 2, message = "State must be 2 characters")
        private String state;

        @NotBlank(message = "ZIP code is required")
        @Pattern(regexp = "^[0-9]{5}-?[0-9]{3}$",
                message = "ZIP code must be in the format XXXXX-XXX or XXXXXXXX")
        private String zipCode;

        private String complement;

    }
}
