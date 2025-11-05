package com.petshop.api.model.mapper;


import com.petshop.api.dto.request.CreateProductDTO;
import com.petshop.api.dto.request.UpdateProductDTO;
import com.petshop.api.dto.response.ProductResponseDTO;
import com.petshop.api.model.entities.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "quantityInStock", defaultValue= "0")
    Product toEntity(CreateProductDTO createProductDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "category.name")
    ProductResponseDTO toResponseDto(Product product);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDTO(UpdateProductDTO updateProductDTO, @MappingTarget Product product);
}

