package com.example.productCatalog.mappers;

import com.example.productCatalog.dto.OrderDto;
import com.example.productCatalog.dto.OrderReturnDto;
import com.example.productCatalog.entities.Orders;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {
    Orders OrderDtoToOrders(OrderDto orderDto);
    OrderReturnDto OrderToOrderReturnDto(Orders orders);
}
