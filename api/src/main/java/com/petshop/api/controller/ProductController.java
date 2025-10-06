package com.petshop.api.controller;

import com.petshop.api.dto.CreateProductDTO;
import com.petshop.api.dto.ProductDTO;
import com.petshop.api.model.enums.ProductCategory;
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
    public ResponseEntity<Page<ProductDTO>> getAllProducts(Pageable pageable) {
        Page<ProductDTO> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable UUID id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductDTO>> getProductByCategory(@PathVariable("category") ProductCategory productCategory,Pageable pageable) {
        Page<ProductDTO> productsByCategory = productService.findProductByCategory(productCategory,pageable);
        return ResponseEntity.ok(productsByCategory);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<ProductDTO>> getProductByName(@PathVariable("name") String name,Pageable pageable) {
        Page<ProductDTO> productsByName = productService.findProductByName(name,pageable);
        return ResponseEntity.ok(productsByName);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
        ProductDTO product = productService.createProduct(createProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable UUID id, @Valid @RequestBody CreateProductDTO createProductDTO) {
        ProductDTO product = productService.updateProduct(id, createProductDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
