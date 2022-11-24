package com.example.productCatalog.mappers;

import com.example.productCatalog.dto.ProductDto;
import com.example.productCatalog.dto.ProductWithIdDto;
import com.example.productCatalog.entities.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {
    Products productDtoToProducts(ProductDto productDto);
    ProductDto productsToProductDto(Products products);
    ProductWithIdDto productsTProductWithIdDto(Products products);

}
