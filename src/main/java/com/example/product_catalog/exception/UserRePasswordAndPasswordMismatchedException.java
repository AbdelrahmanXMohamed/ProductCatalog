package com.example.product_catalog.exception;

import lombok.Getter;

@Getter
public class UserRePasswordAndPasswordMismatchedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

}
