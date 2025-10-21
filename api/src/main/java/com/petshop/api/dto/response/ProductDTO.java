package com.petshop.api.dto.response;

import com.petshop.api.model.enums.ProductCategory;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProductCategory category;
}
