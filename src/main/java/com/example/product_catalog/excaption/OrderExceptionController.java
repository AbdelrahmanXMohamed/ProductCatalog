package com.example.product_catalog.excaption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class OrderExceptionController {
    @ExceptionHandler(value = OrderNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public Map<String,String> orderNotFound(OrderNotFoundException ex) {
        Map<String,String> response= new HashMap<>();
        response.put("message","Order with id:" + ex.getId()+" not found");
        return response;
    }
}
