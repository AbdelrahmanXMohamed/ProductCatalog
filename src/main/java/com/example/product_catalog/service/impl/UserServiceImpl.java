package com.example.product_catalog.service.impl;

import com.example.product_catalog.dto.UserRequestDto;
import com.example.product_catalog.dto.UserResponseDto;
import com.example.product_catalog.entity.Users;
import com.example.product_catalog.exception.UserEmailAlreadyExistException;
import com.example.product_catalog.exception.UserRePasswordAndPasswordMismatchedException;
import com.example.product_catalog.exception.UserUsernameAlreadyExistException;
import com.example.product_catalog.mapper.UserMapper;
import com.example.product_catalog.repository.UsersRepository;
import com.example.product_catalog.security.ApplicationUserRole;
import com.example.product_catalog.security.JwtUtil;
import com.example.product_catalog.security.PasswordConfig;
import com.example.product_catalog.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    @Autowired
    private UsersRepository usersRepository;
    private UserMapper userMapper= Mappers.getMapper(UserMapper.class);
    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository
            .findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
}
    public UserDetails loadUserByEmail(String username) throws UsernameNotFoundException {
        return usersRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if(!userRequestDto.getPassword().equals(userRequestDto.getRePassword())) {
            throw new UserRePasswordAndPasswordMismatchedException();
        }
        if (usersRepository.findByEmail(userRequestDto.getEmail()).isPresent()){
            throw new UserEmailAlreadyExistException();
        }
        if (usersRepository.findByUsername(userRequestDto.getUsername()).isPresent()){
            throw new UserUsernameAlreadyExistException();
        }
        userRequestDto.setPassword(passwordConfig.passwordEncoder().encode(userRequestDto.getPassword()));

        Users user= userMapper.userRequestDtoToUsers(userRequestDto);
        user.setEnabled(true);
        user.setAccountLocked(false);
        user.setApplicationUserRole(ApplicationUserRole.CUSTOMER);
        user=usersRepository.save(user);
        return userMapper.usersToUserResponseDto(user);
    }
}

