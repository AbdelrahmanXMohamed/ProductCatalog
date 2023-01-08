package com.example.product_catalog.controller;

import com.example.product_catalog.dto.ProductResponseDto;
import com.example.product_catalog.dto.ProductWithCategory;
import com.example.product_catalog.entity.Products;
import com.example.product_catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping
    public List<ProductResponseDto> getProducts() // getProducts
    {
        return productService.getProducts();
    }
    @GetMapping("sorted-by-population")

    public List<ProductResponseDto> getPopularProducts()
    {
        return productService.getPopularProducts();
    }
    @PostMapping
    public Products addProducts(@RequestBody @Valid ProductWithCategory productDto)
    {
        return productService.addProduct(productDto);
    }
    @GetMapping("{id}")
    public ProductResponseDto getProductsById(@PathVariable Long id)
    {
        return productService.getProductsById(id);
    }

    @PutMapping("{id}")
    public Products updateProducts(@PathVariable Long id,@RequestBody @Valid ProductWithCategory productDto) throws IOException {
        return productService.updateProducts(id,productDto);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

}
