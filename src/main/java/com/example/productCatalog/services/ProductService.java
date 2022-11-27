package com.example.productCatalog.services;

import com.example.productCatalog.dtos.ProductResponseDto;
import com.example.productCatalog.dtos.ProductWithCategory;
import com.example.productCatalog.entities.Products;

import java.util.List;
public interface ProductService {
    List<ProductResponseDto> retrievingAllProducts();
    Products addProduct(ProductWithCategory productDto);
    Products updateProducts(Long id,ProductWithCategory productDto);
    void deleteProduct(Long id);
    List <ProductResponseDto> retrievingAllProductsOrderedByPopularity();
}
