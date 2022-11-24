package com.example.productCatalog.controllers;

import com.example.productCatalog.dto.ProductDto;
import com.example.productCatalog.dto.ProductWithCategory;
import com.example.productCatalog.dto.ProductWithIdDto;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping
    public List<ProductWithIdDto> retrievingAllProducts()
    {
        return productService.retrievingAllProducts();
    }
    @GetMapping("sortedByPopulation")
    public List<ProductWithIdDto> retrievingAllProductsOrderedByPopularity()
    {
        return productService.retrievingAllProductsOrderedByPopularity();
    }
    @PostMapping
    public Products addProducts(@RequestBody ProductWithCategory productDto)
    {
        return productService.addProduct(productDto);
    }
    @PutMapping("{id}")
    public Products updateProducts(@PathVariable Long id,@RequestBody ProductWithCategory productDto)
    {
        return productService.updateProducts(id,productDto);
    }
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

}
