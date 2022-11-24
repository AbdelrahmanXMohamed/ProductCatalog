package com.example.productCatalog.services;

import com.example.productCatalog.dto.CategoryCreateDto;
import com.example.productCatalog.entities.Categories;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.mappers.CategoryMapper;
import com.example.productCatalog.repositories.CategoriesRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoriesRepository categoriesRepository;
    CategoryMapper categoryMapper= Mappers.getMapper(CategoryMapper.class);
    @Override
    public Categories createCategory(@Valid CategoryCreateDto categoryCreateDto){
        return categoriesRepository.save(categoryMapper.categoryCreateDtoToCategories(categoryCreateDto));
    }
    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public List<Products> getAllProductsByCategories(Long id) {
        return categoriesRepository.findById(id).get().getProducts();
    }

}
