package com.example.product_catalog.excaption;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final Long id;
    public ProductNotFoundException(Long id){
        this.id=id;
    }
}
