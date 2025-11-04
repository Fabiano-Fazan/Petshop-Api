package com.petshop.api.model.mapper;

import com.petshop.api.dto.request.CreateProductCategoryDTO;
import com.petshop.api.dto.request.UpdateProductCategoryDTO;
import com.petshop.api.dto.response.ProductCategoryResponseDTO;
import com.petshop.api.model.entities.ProductCategory;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-31T12:59:01-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class ProductCategoryMapperImpl implements ProductCategoryMapper {

    @Override
    public ProductCategory toEntity(CreateProductCategoryDTO createProductCategoryDTO) {
        if ( createProductCategoryDTO == null ) {
            return null;
        }

        ProductCategory productCategory = new ProductCategory();

        productCategory.setName( createProductCategoryDTO.getName() );
        productCategory.setDescription( createProductCategoryDTO.getDescription() );

        return productCategory;
    }

    @Override
    public ProductCategoryResponseDTO toResponseDto(ProductCategory productCategory) {
        if ( productCategory == null ) {
            return null;
        }

        ProductCategoryResponseDTO productCategoryResponseDTO = new ProductCategoryResponseDTO();

        productCategoryResponseDTO.setId( productCategory.getId() );
        productCategoryResponseDTO.setName( productCategory.getName() );
        productCategoryResponseDTO.setDescription( productCategory.getDescription() );

        return productCategoryResponseDTO;
    }

    @Override
    public void updateProductCategoryFromDTO(UpdateProductCategoryDTO updateProductCategoryDTO, ProductCategory productCategory) {
        if ( updateProductCategoryDTO == null ) {
            return;
        }

        if ( updateProductCategoryDTO.getName() != null ) {
            productCategory.setName( updateProductCategoryDTO.getName() );
        }
        if ( updateProductCategoryDTO.getDescription() != null ) {
            productCategory.setDescription( updateProductCategoryDTO.getDescription() );
        }
    }
}
