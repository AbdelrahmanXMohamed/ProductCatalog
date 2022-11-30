package com.example.product_catalog.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {
    @NotBlank(message = "This username is required")
    private String username;
    @NotBlank(message = "This password is required")
    private String password;
    @NotBlank(message = "This re-password is required")
    private String  rePassword;
    @NotBlank(message = "This email is required")
    private String email;
}
