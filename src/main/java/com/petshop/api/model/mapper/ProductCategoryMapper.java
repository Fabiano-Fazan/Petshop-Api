package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateProductCategoryDTO;
import com.petshop.api.dto.request.UpdateProductCategoryDTO;
import com.petshop.api.dto.response.ProductCategoryResponseDTO;
import com.petshop.api.model.entities.ProductCategory;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategory toEntity(CreateProductCategoryDTO createProductCategoryDTO);

    ProductCategoryResponseDTO toResponseDto(ProductCategory productCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductCategoryFromDTO(UpdateProductCategoryDTO updateProductCategoryDTO, @MappingTarget ProductCategory productCategory);
}
