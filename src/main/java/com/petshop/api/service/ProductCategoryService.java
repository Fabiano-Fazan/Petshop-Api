package com.petshop.api.service;

import com.petshop.api.dto.request.CreateProductCategoryDTO;
import com.petshop.api.dto.request.UpdateProductCategoryDTO;
import com.petshop.api.dto.response.ProductCategoryResponseDTO;
import com.petshop.api.exception.ResourceNotFoundException;
import com.petshop.api.model.entities.ProductCategory;
import com.petshop.api.model.mapper.ProductCategoryMapper;
import com.petshop.api.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryResponseDTO getProductCategoryById(UUID id){
        return productCategoryRepository.findById(id)
                .map(productCategoryMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public Page<ProductCategoryResponseDTO> getAllProductCategories(Pageable pageable) {
        return productCategoryRepository.findAll(pageable)
                .map(productCategoryMapper::toResponseDto);
    }

    public Page<ProductCategoryResponseDTO> getByName(String name, Pageable pageable){
        return productCategoryRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(productCategoryMapper::toResponseDto);
    }

    @Transactional
    public ProductCategoryResponseDTO createProductCategory(CreateProductCategoryDTO createProductCategoryDTO){
        ProductCategory productCategory = productCategoryMapper.toEntity(createProductCategoryDTO);
        ProductCategory savedProductCategory = productCategoryRepository.save(productCategory);
        return productCategoryMapper.toResponseDto(savedProductCategory);
    }

    @Transactional
    public ProductCategoryResponseDTO updateProductCategory(UUID id, UpdateProductCategoryDTO updateProductCategoryDTO) {
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + id));
        productCategoryMapper.updateProductCategoryFromDTO(updateProductCategoryDTO, productCategory);
        productCategoryRepository.save(productCategory);
        return productCategoryMapper.toResponseDto(productCategory);
    }

    @Transactional
    public void deleteProductCategory(UUID id) {
        if (!productCategoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with ID: " + id);
            }
        productCategoryRepository.deleteById(id);
        }
    }




