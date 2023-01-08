package com.example.product_catalog.exception.controller;

import com.example.product_catalog.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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
    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> usernameNotFoundException(UsernameNotFoundException e) {
        Map<String,String> response= new HashMap<>();
        response.put("message",e.getMessage());
        return response;
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String,String> userTokenExpiredException() {
        Map<String,String> response= new HashMap<>();
        response.put("message","Token Expired");
        return response;
    }

    @ExceptionHandler(value= SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String,String> userInvalidTokenException(SignatureException ex) {
        Map<String,String> response= new HashMap<>();
        response.put("message","Invalid signature");
        return response;
    }
}
