package com.example.productCatalog.mappers;

import com.example.productCatalog.dto.CategoryCreateDto;
import com.example.productCatalog.dto.CategoryDto;
import com.example.productCatalog.entities.Categories;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    CategoryDto categoriesToCategoryDto(Categories categories);
    Categories categoryCreateDtoToCategories(CategoryCreateDto categoryCreateDto);
}
