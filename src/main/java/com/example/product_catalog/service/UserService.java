package com.example.product_catalog.service;

import com.example.product_catalog.dto.UserRequestDto;
import com.example.product_catalog.dto.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashMap;
import java.util.Map;

public interface UserService extends UserDetailsService {
    UserResponseDto createUser(UserRequestDto user);
    UserDetails loadUserByEmail(String username);
}
