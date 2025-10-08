package com.petshop.api.model.entities;

import com.petshop.api.model.enums.TypeMoviment;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "stock_moviments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMoviment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeMoviment type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime dateMoviment;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    public static StockMoviment newInput(Product product, Integer quantity, String description){
        return StockMoviment.builder()
                .product(product)
                .quantity(quantity)
                .type(TypeMoviment.INPUT)
                .dateMoviment(LocalDateTime.now())
                .description(description)
                .build();
    }

    public static StockMoviment newOutput(Product product, Integer quantity, String description, Sale sale){
        return StockMoviment.builder()
                .product(product)
                .quantity(quantity)
                .type(TypeMoviment.OUTPUT)
                .dateMoviment(LocalDateTime.now())
                .description(description)
                .sale(sale)
                .build();
    }

}
