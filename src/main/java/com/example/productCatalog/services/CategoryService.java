package com.example.productCatalog.services;

import com.example.productCatalog.dto.CategoryCreateDto;
import com.example.productCatalog.entities.Categories;
import com.example.productCatalog.entities.Products;

import java.util.List;

public interface CategoryService {
    Categories createCategory(CategoryCreateDto categoryCreateDto);
    List<Categories> getAllCategories();
    List<Products> getAllProductsByCategories(Long id);
}
