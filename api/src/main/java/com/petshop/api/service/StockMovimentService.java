package com.petshop.api.service;

import com.petshop.api.exception.InsufficientStockException;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.entities.Sale;
import com.petshop.api.model.entities.StockMoviment;
import com.petshop.api.repository.ProductRepository;
import com.petshop.api.repository.StockMovimentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class StockMovimentService {
    private final StockMovimentRepository stockMovimentRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void registerOutput(Product product, Integer quantity, String description, Sale sale){
        if (product.getQuantityInStock() < quantity ){
            throw new InsufficientStockException("Not enough stock");
        }

        product.setQuantityInStock(product.getQuantityInStock() - quantity);
        productRepository.save(product);

        StockMoviment stockMoviment = StockMoviment.newOutput(product, quantity, description, sale);
        stockMovimentRepository.save(stockMoviment);
    }

    @Transactional
    public void registerInput(Product product, Integer quantity, String description){
        product.setQuantityInStock(product.getQuantityInStock() + quantity);
        productRepository.save(product);

        StockMoviment stockMoviment = StockMoviment.newInput(product, quantity, description);
        stockMovimentRepository.save(stockMoviment);
    }
}
