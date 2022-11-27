package com.example.productCatalog.services;

import com.example.productCatalog.dtos.OrderDto;
import com.example.productCatalog.dtos.OrderReturnDto;

import java.util.List;

public interface OrderService {
    OrderReturnDto buyListOfProducts(OrderDto orderDto);
    OrderReturnDto  getOrderDetails(Long id);
}
