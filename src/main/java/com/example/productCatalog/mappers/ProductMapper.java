package com.example.productCatalog.mappers;

import com.example.productCatalog.dtos.OrderedProductDto;
import com.example.productCatalog.dtos.ProductDto;
import com.example.productCatalog.dtos.ProductResponseDto;
import com.example.productCatalog.entities.Products;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Products productDtoToProducts(ProductDto productDto);
    ProductDto productsToProductDto(Products products);
    ProductResponseDto productsToProductWithIdDto(Products products);
    OrderedProductDto productsToOrderedProductDto(Products products);

}
