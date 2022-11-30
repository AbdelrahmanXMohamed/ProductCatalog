package com.example.product_catalog.exception;

import lombok.Data;

@Data
public class CategoryNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final Long id;
    public CategoryNotFoundException(Long id)
    {
        this.id=id;
    }
}
