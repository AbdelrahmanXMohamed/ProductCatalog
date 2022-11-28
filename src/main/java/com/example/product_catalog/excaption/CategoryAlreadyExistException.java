package com.example.product_catalog.excaption;

import com.example.product_catalog.dto.CategoryDto;
import lombok.Data;

@Data
public class CategoryAlreadyExistException extends  RuntimeException{
    private static final long serialVersionUID = 1L;
    private CategoryDto category;
    public CategoryAlreadyExistException(CategoryDto category)
    {
        this.category=category;
    }
}
