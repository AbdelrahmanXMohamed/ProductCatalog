package com.example.productCatalog.mappers;

import com.example.productCatalog.dtos.OrderRequestDto;
import com.example.productCatalog.dtos.OrderResponseDto;
import com.example.productCatalog.entities.Orders;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Orders OrderDtoToOrders(OrderRequestDto orderRequestDto);
    OrderResponseDto OrderToOrderReturnDto(Orders orders);
}
