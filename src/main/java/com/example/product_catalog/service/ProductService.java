package com.example.product_catalog.service;

import com.example.product_catalog.dto.ProductResponseDto;
import com.example.product_catalog.dto.ProductWithCategory;
import com.example.product_catalog.entity.Products;

import java.io.IOException;
import java.util.List;
public interface ProductService {
    List<ProductResponseDto> getProducts();
    Products addProduct(ProductWithCategory productDto);
    Products updateProducts(Long id,ProductWithCategory productDto) throws IOException;
    void deleteProduct(Long id);
    List <ProductResponseDto> getPopularProducts();

    ProductResponseDto getProductsById(Long id);

}
