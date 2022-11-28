package com.example.product_catalog.mapper;

import com.example.product_catalog.dto.OrderRequestDto;
import com.example.product_catalog.dto.OrderResponseDto;
import com.example.product_catalog.entity.Orders;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Orders orderDtoToOrders(OrderRequestDto orderRequestDto);
    OrderResponseDto orderToOrderReturnDto(Orders orders);
}
