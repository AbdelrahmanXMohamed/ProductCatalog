package com.example.product_catalog.exception;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final Long id;
    public OrderNotFoundException(Long id){
        this.id=id;
    }
}
