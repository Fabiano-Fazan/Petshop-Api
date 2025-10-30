package com.petshop.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientDTO {

    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @Pattern(
            regexp = "^\\(?\\d{2}\\)?[\\s-]?9?\\d{4}-?\\d{4}$",
            message = "Phone number must be in the format (XX) XXXXX-XXXX or (XX) XXXX-XXXX"
    )
    private String phone;

    @Valid
    private AddressData address;

    @Getter
    @Setter
    public static class AddressData {

        @Size(min = 1, message = "Street cannot be empty")
        private String street;

        @Size(min = 1, message = "City cannot be empty")
        private String city;

        @Size(min = 2, max = 2, message = "State must be 2 characters")
        private String state;

        @Pattern(regexp = "^[0-9]{5}-?[0-9]{3}$",
                message = "ZIP code must be in the format XXXXX-XXX or XXXXXXXX")
        private String zipCode;

        private String complement;

    }
}
