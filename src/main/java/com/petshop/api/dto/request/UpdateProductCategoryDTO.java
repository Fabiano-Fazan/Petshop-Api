package com.petshop.api.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductCategoryDTO {

    @Size(min =3, max = 50, message = "The name must be between 3 and 50 characters")
    private String name;

    @Size(min = 10, max = 255, message = "The description must be between 10 and 255 characters")
    private String description;


}
