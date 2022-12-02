package com.example.product_catalog.exception.controller;

import com.example.product_catalog.exception.ProductNotFoundException;
import com.example.product_catalog.exception.ProductOutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(value = ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public  Map<String,String> productNotFound(ProductNotFoundException ex) {
        Map<String,String> response= new HashMap<>();
        response.put("message","Product with id: " + ex.getId()+" not found");
        return response;
    }
    @ExceptionHandler(value = ProductOutOfStockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String ,String> productOutOfStock(ProductOutOfStockException ex)
    {
        Map<String,String> response= new HashMap<>();
        response.put("message","Product with id: " + ex.getProductId()+" is out of stock");
        return response;
    }
}
