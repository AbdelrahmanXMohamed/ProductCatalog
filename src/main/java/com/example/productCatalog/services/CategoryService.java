package com.example.productCatalog.services;

import com.example.productCatalog.dtos.CategoryCreateDto;
import com.example.productCatalog.dtos.CategoryDto;
import com.example.productCatalog.entities.Categories;
import com.example.productCatalog.entities.Products;

import java.util.List;

public interface CategoryService {
    Categories createCategory(CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getAllCategories();
    List<Products> getAllProductsByCategories(Long id);
}
