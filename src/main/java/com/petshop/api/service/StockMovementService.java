package com.petshop.api.service;

import com.petshop.api.dto.request.CreateStockMovementDTO;
import com.petshop.api.exception.InsufficientStockException;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.entities.Sale;
import com.petshop.api.model.entities.StockMovement;
import com.petshop.api.repository.ProductRepository;
import com.petshop.api.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class StockMovementService {
    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void registerInput(CreateStockMovementDTO createStockMovementDTO){
        Product product = productRepository.findById(createStockMovementDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + createStockMovementDTO.getProductId()));
        this.registerInput(product, createStockMovementDTO.getQuantity(), createStockMovementDTO.getDescription(), createStockMovementDTO.getInvoice(), createStockMovementDTO.getPrice());

    }

    @Transactional
    public void registerInput(Product product, Integer quantity, String description, String invoice, BigDecimal price){
        product.setQuantityInStock(product.getQuantityInStock() + quantity);
        productRepository.save(product);

        StockMovement stockMovement = StockMovement.newInput(product, quantity, description, invoice, price);
        stockMovementRepository.save(stockMovement);
    }

    @Transactional
    public void registerOutput(CreateStockMovementDTO createStockMovementDTO){
        Product product = productRepository.findById(createStockMovementDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + createStockMovementDTO.getProductId()));
        this.registerOutput(product, createStockMovementDTO.getQuantity(), createStockMovementDTO.getDescription(), null, null);
    }

    @Transactional
    public void registerOutput(Product product, Integer quantity, String description, BigDecimal price, Sale sale){
        if(product.getQuantityInStock() < quantity){
            throw new InsufficientStockException("Not enough stock for product " + product.getName() + "Requested: " + quantity + " Available: " + product.getQuantityInStock());
        }
        product.setQuantityInStock(product.getQuantityInStock() - quantity);
        productRepository.save(product);

        StockMovement stockMovement = StockMovement.newOutput(product, quantity,description,sale, null);
        stockMovementRepository.save(stockMovement);
    }
}