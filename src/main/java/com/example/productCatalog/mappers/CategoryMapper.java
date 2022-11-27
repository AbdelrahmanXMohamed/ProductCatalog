package com.example.productCatalog.mappers;

import com.example.productCatalog.dtos.CategoryCreateDto;
import com.example.productCatalog.dtos.CategoryDto;
import com.example.productCatalog.entities.Categories;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    CategoryDto categoriesToCategoryDto(Categories categories);
    Categories categoryCreateDtoToCategories(CategoryCreateDto categoryCreateDto);
}
