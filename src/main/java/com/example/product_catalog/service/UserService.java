package com.example.product_catalog.service;

import com.example.product_catalog.dto.UserRequestDto;
import com.example.product_catalog.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto user);
}
