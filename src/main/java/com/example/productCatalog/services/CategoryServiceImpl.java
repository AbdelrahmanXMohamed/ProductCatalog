package com.example.productCatalog.services;

import com.example.productCatalog.dtos.CategoryCreateDto;
import com.example.productCatalog.dtos.CategoryDto;
import com.example.productCatalog.entities.Categories;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.mappers.CategoryMapper;
import com.example.productCatalog.repositories.CategoriesRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoriesRepository categoriesRepository;
    CategoryMapper categoryMapper= Mappers.getMapper(CategoryMapper.class);
    @Override
    public Categories createCategory(CategoryCreateDto categoryCreateDto){
        if (categoriesRepository.findByName(categoryCreateDto.getName())!=null)
            return categoriesRepository.findByName(categoryCreateDto.getName());

        return categoriesRepository
                .save(categoryMapper.categoryCreateDtoToCategories(categoryCreateDto));
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
        return categoriesRepository.findById(id).get().getProducts();
    }

}
