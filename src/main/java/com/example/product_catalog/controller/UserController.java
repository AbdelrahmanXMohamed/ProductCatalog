package com.example.product_catalog.controller;

import com.example.product_catalog.dto.AuthenticationRequestDto;
import com.example.product_catalog.dto.AuthenticationResponseDto;
import com.example.product_catalog.dto.UserRequestDto;
import com.example.product_catalog.dto.UserResponseDto;
import com.example.product_catalog.security.JwtUtil;
import com.example.product_catalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto signUp(@RequestBody @Valid UserRequestDto userRequestDto) {
        System.out.println(userRequestDto);

        return userService.createUser(userRequestDto);
    }
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDto signIn(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
        final UserDetails userDetails =userDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());
        String jwt= jwtUtil.generateToken(userDetails);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequestDto.getUsername(),
                            authenticationRequestDto.getPassword()
                    )
            );
        } catch (BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password",e);
        }
;
        return new AuthenticationResponseDto(jwt);
    }

}
