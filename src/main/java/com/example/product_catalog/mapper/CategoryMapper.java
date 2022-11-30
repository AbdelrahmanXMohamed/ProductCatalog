package com.example.product_catalog.mapper;

import com.example.product_catalog.dto.CategoryCreateDto;
import com.example.product_catalog.dto.CategoryDto;
import com.example.product_catalog.entity.Categories;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryDto categoriesToCategoryDto(Categories categories);
    Categories categoryCreateDtoToCategories(CategoryCreateDto categoryCreateDto);
}
