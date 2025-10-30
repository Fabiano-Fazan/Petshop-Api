package com.petshop.api.controller;

import com.petshop.api.dto.request.CreateProductDTO;
import com.petshop.api.dto.request.UpdateProductDTO;
import com.petshop.api.dto.response.ProductResponseDTO;
import com.petshop.api.model.entities.ProductCategory;
import com.petshop.api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(Pageable pageable) {
        Page<ProductResponseDTO> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        ProductResponseDTO productById = productService.getProductById(id);
        return ResponseEntity.ok(productById);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductResponseDTO>> getProductByCategory(@PathVariable("category") ProductCategory productCategory, Pageable pageable) {
        Page<ProductResponseDTO> productsByCategory = productService.getProductByCategory(productCategory,pageable);
        return ResponseEntity.ok(productsByCategory);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<ProductResponseDTO>> getProductByName(@PathVariable("name") String name, Pageable pageable) {
        Page<ProductResponseDTO> productsByName = productService.getProductByName(name,pageable);
        return ResponseEntity.ok(productsByName);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(createProductDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody UpdateProductDTO updateProductDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(id, updateProductDTO);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
