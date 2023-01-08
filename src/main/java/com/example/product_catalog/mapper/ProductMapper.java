package com.example.product_catalog.mapper;

import com.example.product_catalog.dto.OrderedProductDto;
import com.example.product_catalog.dto.ProductDto;
import com.example.product_catalog.dto.ProductResponseDto;
import com.example.product_catalog.dto.ProductWithCategory;
import com.example.product_catalog.entity.Products;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Products productDtoToProducts(ProductDto productDto);
    ProductDto productsToProductDto(Products products);
    ProductResponseDto productsToProductWithIdDto(Products products);
    OrderedProductDto productsToOrderedProductDto(Products products);
    Products productWithCategoryDtoToProducts(ProductWithCategory productWithCategory);
    ProductResponseDto productsToProductResponseDto(Products products);

}
