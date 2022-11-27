package com.example.productCatalog.excaptions;

import lombok.Data;

@Data
public class CategoryNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private Long id;
    public CategoryNotFoundException(Long id)
    {
        this.id=id;
    }
}
