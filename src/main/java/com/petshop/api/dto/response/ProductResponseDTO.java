package com.petshop.api.dto.response;


import com.petshop.api.model.entities.ProductCategory;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
}
