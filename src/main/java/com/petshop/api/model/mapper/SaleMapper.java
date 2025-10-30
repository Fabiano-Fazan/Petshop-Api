package com.petshop.api.model.mapper;

import com.petshop.api.dto.response.ProductSaleResponseDTO;
import com.petshop.api.dto.response.SaleResponseDTO;
import com.petshop.api.model.entities.ProductSale;
import com.petshop.api.model.entities.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    @Mapping(source = "client.name", target = "clientName")
    SaleResponseDTO toResponseDto(Sale sale);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    ProductSaleResponseDTO toProductSaleDto(ProductSale productSale);

}
