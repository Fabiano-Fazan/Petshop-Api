package com.petshop.api.model.entities;


import com.petshop.api.dto.CreateProductDTO;
import com.petshop.api.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(CreateProductDTO createProductDTO);
    ProductDTO toDto(Product product);
    void updateProductFromDTO(CreateProductDTO createProductDTO, @MappingTarget Product product);
}
