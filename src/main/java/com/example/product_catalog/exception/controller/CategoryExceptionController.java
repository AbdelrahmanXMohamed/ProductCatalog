package com.example.product_catalog.exception.controller;

import com.example.product_catalog.exception.CategoryAlreadyExistException;
import com.example.product_catalog.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CategoryExceptionController {
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> categoryNotFound(CategoryNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "Category with id:" + ex.getId() + " not found");
        return error;
    }
    @ExceptionHandler(CategoryAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,Object> categoryAlreadyExist(CategoryAlreadyExistException ex)
    {
        Map<String, Object> error = new HashMap<>();
        error.put("message", "Category already exist");
        error.put("category",ex.getCategory());
        return error;
    }
}
