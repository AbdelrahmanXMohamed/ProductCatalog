package com.example.productCatalog.services;

import com.example.productCatalog.dto.ProductDto;
import com.example.productCatalog.dto.ProductWithCategory;
import com.example.productCatalog.dto.ProductWithIdDto;
import com.example.productCatalog.entities.Products;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProductService {
    List<ProductWithIdDto> retrievingAllProducts();
    Products addProduct(ProductWithCategory productDto);
    Products updateProducts(Long id,ProductWithCategory productDto);
    void deleteProduct(Long id);
    List <ProductWithIdDto> retrievingAllProductsOrderedByPopularity();
}
