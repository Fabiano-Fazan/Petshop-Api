package com.petshop.api.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductCategoryDto {

    @Schema(description = "Category name", example = "Toys")
    @Size(min =3, max = 50, message = "The name must be between 3 and 50 characters")
    private String name;

    @Schema(description = "Category description", example = "Toys for pets")
    private String description;

}
