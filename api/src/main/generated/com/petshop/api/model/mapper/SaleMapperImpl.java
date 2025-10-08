package com.petshop.api.model.mapper;

import com.petshop.api.dto.ProductSaleDTO;
import com.petshop.api.dto.SaleDTO;
import com.petshop.api.model.entities.Client;
import com.petshop.api.model.entities.Product;
import com.petshop.api.model.entities.ProductSale;
import com.petshop.api.model.entities.Sale;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-07T21:08:51-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Amazon.com Inc.)"
)
@Component
public class SaleMapperImpl implements SaleMapper {

    @Override
    public SaleDTO toDto(Sale sale) {
        if ( sale == null ) {
            return null;
        }

        SaleDTO saleDTO = new SaleDTO();

        saleDTO.setClientName( saleClientName( sale ) );
        saleDTO.setId( sale.getId() );
        saleDTO.setSaleDate( sale.getSaleDate() );
        saleDTO.setTotalValue( sale.getTotalValue() );
        saleDTO.setProductSales( productSaleListToProductSaleDTOList( sale.getProductSales() ) );

        return saleDTO;
    }

    @Override
    public ProductSaleDTO toProductSaleDto(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }

        ProductSaleDTO productSaleDTO = new ProductSaleDTO();

        productSaleDTO.setProductId( productSaleProductId( productSale ) );
        productSaleDTO.setProductName( productSaleProductName( productSale ) );
        productSaleDTO.setQuantity( productSale.getQuantity() );
        productSaleDTO.setUnitPrice( productSale.getUnitPrice() );

        return productSaleDTO;
    }

    private String saleClientName(Sale sale) {
        if ( sale == null ) {
            return null;
        }
        Client client = sale.getClient();
        if ( client == null ) {
            return null;
        }
        String name = client.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected List<ProductSaleDTO> productSaleListToProductSaleDTOList(List<ProductSale> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductSaleDTO> list1 = new ArrayList<ProductSaleDTO>( list.size() );
        for ( ProductSale productSale : list ) {
            list1.add( toProductSaleDto( productSale ) );
        }

        return list1;
    }

    private UUID productSaleProductId(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }
        Product product = productSale.getProduct();
        if ( product == null ) {
            return null;
        }
        UUID id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productSaleProductName(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }
        Product product = productSale.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
