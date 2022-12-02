package com.example.product_catalog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderedProductMoreThanOrderLimitException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private Long productId;
    private int productOrderedLimit;
    private int orderedQuantity;
    private int previousOrderedQuantity;
}
