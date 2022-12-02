package com.example.product_catalog.exception.controller;

import com.example.product_catalog.exception.UserEmailAlreadyExistException;
import com.example.product_catalog.exception.UserRePasswordAndPasswordMismatchedException;
import com.example.product_catalog.exception.UserUsernameAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(value = UserRePasswordAndPasswordMismatchedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> userRePasswordAndPasswordMismatched() {
        Map<String,String> response= new HashMap<>();
        response.put("message","Password and re-password doesn't match");
        return response;
    }
    @ExceptionHandler(value = UserEmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> userEmailAlreadyExistException() {
        Map<String,String> response= new HashMap<>();
        response.put("message","This email already exist");
        return response;
    }
    @ExceptionHandler(value = UserUsernameAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> userUsernameAlreadyExistException() {
        Map<String,String> response= new HashMap<>();
        response.put("message","This username already exist");
        return response;
    }
}
