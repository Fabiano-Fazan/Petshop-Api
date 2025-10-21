package com.petshop.api.model.mapper;


import com.petshop.api.dto.request.CreateProductDTO;
import com.petshop.api.dto.response.ProductDTO;
import com.petshop.api.model.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "quantityInStock", defaultValue= "0")
    Product toEntity(CreateProductDTO createProductDTO);

    ProductDTO toDto(Product product);

    void updateProductFromDTO(CreateProductDTO createProductDTO, @MappingTarget Product product);
}
