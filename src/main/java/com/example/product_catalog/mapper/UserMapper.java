package com.example.product_catalog.mapper;

import com.example.product_catalog.dto.UserRequestDto;
import com.example.product_catalog.entity.Users;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    Users userRequestDtoToUsers(UserRequestDto userRequestDto);
}
