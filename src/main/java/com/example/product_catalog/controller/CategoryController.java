package com.example.product_catalog.controller;

import com.example.product_catalog.dto.CategoryCreateDto;
import com.example.product_catalog.dto.CategoryDto;
import com.example.product_catalog.entity.Categories;
import com.example.product_catalog.entity.Products;
import com.example.product_catalog.service.CategoryService;
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
