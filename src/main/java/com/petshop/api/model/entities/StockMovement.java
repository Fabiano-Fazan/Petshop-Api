package com.petshop.api.model.entities;

import com.petshop.api.model.enums.TypeMoviment;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "stock_movements")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMoviment type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime dateMovement;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    private String invoice;

    public static StockMovement newInput(Product product, Integer quantity, String description, String invoice, BigDecimal price){
        return StockMovement.builder()
                .product(product)
                .quantity(quantity)
                .type(TypeMoviment.INPUT)
                .dateMovement(LocalDateTime.now())
                .description(description)
                .invoice(invoice)
                .price(price)
                .build();
    }

    public static StockMovement newOutput(Product product, Integer quantity, String description, Sale sale, BigDecimal price){
        return StockMovement.builder()
                .product(product)
                .quantity(quantity)
                .type(TypeMoviment.OUTPUT)
                .dateMovement(LocalDateTime.now())
                .description(description)
                .sale(sale)
                .price(price)
                .build();
    }

}
