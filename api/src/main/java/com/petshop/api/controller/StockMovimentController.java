package com.petshop.api.controller;

import com.petshop.api.dto.CreateStockMoviment;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Product;
import com.petshop.api.repository.ProductRepository;
import com.petshop.api.service.StockMovimentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockMovimentController {
    private final StockMovimentService stockMovimentService;
    private final ProductRepository productRepository;

    @PostMapping("/input")
    public ResponseEntity<Void> giveInputStock(@RequestBody @Valid CreateStockMoviment createStockMoviment){
        Product product = productRepository.findById(createStockMoviment.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: "+ createStockMoviment.getProductId()));

        stockMovimentService.registerInput(product, createStockMoviment.getQuantity(),createStockMoviment.getDescription());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/output")
    public ResponseEntity<Void> giveOutputStock(@RequestBody @Valid CreateStockMoviment createStockMoviment){
        Product product = productRepository.findById(createStockMoviment.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + createStockMoviment.getProductId()));

        stockMovimentService.registerOutput(product, createStockMoviment.getQuantity(),createStockMoviment.getDescription(),null);

        return ResponseEntity.ok().build();
    }
}
