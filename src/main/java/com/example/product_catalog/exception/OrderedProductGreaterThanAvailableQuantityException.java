package com.example.product_catalog.exception;

import lombok.Getter;

@Getter
public class OrderedProductGreaterThanAvailableQuantityException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final int availableProductQuantity;
    private final int orderedProductQuantity;
    private final Long productId;
   public OrderedProductGreaterThanAvailableQuantityException(Long productId,int availableProductQuantity,int orderedProductQuantity)
   {
       this.productId=productId;
       this.availableProductQuantity=availableProductQuantity;
       this.orderedProductQuantity=orderedProductQuantity;
   }
}
