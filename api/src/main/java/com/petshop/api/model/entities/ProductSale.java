package com.petshop.api.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "product_sales")
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id",nullable = false)
    private Sale sale;

    private Integer quantity;

    private BigDecimal unitPrice;
}
