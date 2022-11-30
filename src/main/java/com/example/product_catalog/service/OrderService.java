package com.example.product_catalog.service;

import com.example.product_catalog.dto.OrderRequestDto;
import com.example.product_catalog.dto.OrderResponseDto;
import com.example.product_catalog.exception.ProductNotFoundException;

public interface OrderService {
    OrderResponseDto buyProducts(OrderRequestDto orderRequestDto) throws ProductNotFoundException;
    OrderResponseDto getOrderDetails(Long id);
}
