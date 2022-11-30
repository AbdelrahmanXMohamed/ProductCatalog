package com.example.product_catalog.service.impl;

import com.example.product_catalog.dto.UserRequestDto;
import com.example.product_catalog.dto.UserResponseDto;
import com.example.product_catalog.repository.UsersRepository;
import com.example.product_catalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
//    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    @Autowired
    private UsersRepository usersRepository;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return usersRepository
//                .findByEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
//    }


    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if(!userRequestDto.getPassword().equals(userRequestDto.getRePassword()))
        {

        }
            return null;
    }
}

