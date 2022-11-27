package com.example.productCatalog.controllers;

import com.example.productCatalog.dtos.ProductResponseDto;
import com.example.productCatalog.dtos.ProductWithCategory;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping
    public List<ProductResponseDto> retrievingAllProducts() // getProducts
    {
        return productService.retrievingAllProducts();
    }
    @GetMapping("sorted-by-population")
    public List<ProductResponseDto> retrievingAllProductsOrderedByPopularity()
    {
        return productService.retrievingAllProductsOrderedByPopularity();
    }
    @PostMapping
    public Products addProducts(@RequestBody @Valid ProductWithCategory productDto)
    {
        return productService.addProduct(productDto);
    }
    @PutMapping("{id}")
    public Products updateProducts(@PathVariable Long id,@RequestBody @Valid ProductWithCategory productDto)
    {
        return productService.updateProducts(id,productDto);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

}
