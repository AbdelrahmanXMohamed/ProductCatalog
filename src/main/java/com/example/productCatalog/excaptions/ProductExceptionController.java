package com.example.productCatalog.excaptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> exception(ProductNotFoundException exception) {
        Map<String,String> response= new HashMap<>();
        response.put("message","Product with id: " + exception.getId()+" not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
