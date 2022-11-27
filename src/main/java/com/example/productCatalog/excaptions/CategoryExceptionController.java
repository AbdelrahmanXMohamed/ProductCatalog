package com.example.productCatalog.excaptions;

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
    public Map<String,String> CategoryNotFound(CategoryNotFoundException ex)
 {
     Map<String,String> error=new HashMap<>();
     error.put("message","Category with id:" + ex.getId()+" not found");
     return error;
 }
}
