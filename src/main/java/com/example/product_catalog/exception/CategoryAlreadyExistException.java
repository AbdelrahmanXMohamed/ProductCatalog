package com.example.product_catalog.exception;

import com.example.product_catalog.dto.CategoryDto;
import lombok.Getter;


@Getter
public class CategoryAlreadyExistException extends  RuntimeException {
    private static final long serialVersionUID = 1L;
    private final CategoryDto category;
    public CategoryAlreadyExistException(CategoryDto category)
    {
        this.category=category;
    }
}
