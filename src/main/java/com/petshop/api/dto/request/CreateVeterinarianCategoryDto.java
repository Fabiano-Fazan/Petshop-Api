package com.petshop.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVeterinarianCategoryDto {

    @Schema(description = "Category name", example = "Surgeon")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Category description", example = "Performs surgeries")
    private String description;

}
