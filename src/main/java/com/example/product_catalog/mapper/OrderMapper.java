package com.example.product_catalog.mapper;

import com.example.product_catalog.dto.OrderRequestDto;
import com.example.product_catalog.dto.OrderResponseDto;
import com.example.product_catalog.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OrderMapper {
    Orders orderDtoToOrders(OrderRequestDto orderRequestDto);
    OrderResponseDto orderToOrderReturnDto(Orders order);
//    List<OrderResponseDto> orderListToOrderReturnDtoList(List<Orders> order);
}
