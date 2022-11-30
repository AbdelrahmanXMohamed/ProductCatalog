package com.example.product_catalog.service.impl;

import com.example.product_catalog.dto.CategoryCreateDto;
import com.example.product_catalog.dto.CategoryDto;
import com.example.product_catalog.entity.Categories;
import com.example.product_catalog.entity.Products;
import com.example.product_catalog.exception.CategoryAlreadyExistException;
import com.example.product_catalog.exception.CategoryNotFoundException;
import com.example.product_catalog.mapper.CategoryMapper;
import com.example.product_catalog.repository.CategoriesRepository;
import com.example.product_catalog.service.CategoryService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoriesRepository categoriesRepository;
    CategoryMapper categoryMapper= Mappers.getMapper(CategoryMapper.class);
    @Override
    public Categories createCategory(CategoryCreateDto categoryCreateDto){
        Optional<Categories> categoriesOptional=categoriesRepository.findByName(categoryCreateDto.getName());
        if (categoriesOptional.isPresent())
            throw new CategoryAlreadyExistException(categoryMapper.categoriesToCategoryDto(categoriesOptional.get()) );
        return categoriesRepository.save(categoryMapper.categoryCreateDtoToCategories(categoryCreateDto));
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        return categoriesRepository.findAll()
                .stream()
                .map(category->categoryMapper.categoriesToCategoryDto(category))
                .collect(Collectors.toList());
    }
    @Override
    public List<Products> getAllProductsByCategories(Long id) {
        Optional<Categories> categoriesOptional=categoriesRepository.findById(id);
        if (!categoriesOptional.isPresent()){
            throw new CategoryNotFoundException(id);
        }
        return categoriesOptional.get().getProductItems().stream().filter(Products::getStatus).collect(Collectors.toList());
    }

}
