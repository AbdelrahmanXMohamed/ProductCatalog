package com.example.product_catalog.service;

import com.example.product_catalog.dto.CategoryCreateDto;
import com.example.product_catalog.dto.CategoryDto;
import com.example.product_catalog.entity.Categories;
import com.example.product_catalog.entity.Products;

import java.util.List;

public interface CategoryService {
    Categories createCategory(CategoryCreateDto categoryCreateDto);
    List<CategoryDto> getAllCategories();
    List<Products> getAllProductsByCategories(Long id);
}
