package com.example.product_catalog.exception;

import lombok.Getter;

@Getter
public class ProductOutOfStockException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final Long productId;
    public ProductOutOfStockException(Long productId) {
        this.productId = productId;
    }
}
