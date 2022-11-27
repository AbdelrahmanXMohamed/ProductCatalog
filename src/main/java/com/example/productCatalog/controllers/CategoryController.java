package com.example.productCatalog.controllers;

import com.example.productCatalog.dtos.CategoryCreateDto;
import com.example.productCatalog.dtos.CategoryDto;
import com.example.productCatalog.entities.Categories;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public Categories createCategory(@RequestBody @Valid CategoryCreateDto categoryCreateDto)
    {
        return categoryService.createCategory(categoryCreateDto);
    }
    @GetMapping
    public List<CategoryDto> getAllCategories()
    {
        return categoryService.getAllCategories();
    }
    @GetMapping("{id}")
    public List<Products> getAllProductsByCategories(@PathVariable Long id)
    {
        return categoryService.getAllProductsByCategories(id);
    }

}
