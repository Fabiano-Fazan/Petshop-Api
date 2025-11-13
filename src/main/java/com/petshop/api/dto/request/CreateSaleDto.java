package com.petshop.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateSaleDto {

    @NotNull(message = "Client ID is required")
    private UUID clientId;

    @NotEmpty(message = "Product sales list is required")
    @Valid
    private List<CreateProductSaleDto> productSales;

}
