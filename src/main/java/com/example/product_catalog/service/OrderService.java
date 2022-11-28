package com.example.product_catalog.service;

import com.example.product_catalog.dto.OrderRequestDto;
import com.example.product_catalog.dto.OrderResponseDto;
import com.example.product_catalog.excaption.ProductNotFoundException;

public interface OrderService {
    OrderResponseDto buyProducts(OrderRequestDto orderRequestDto) throws ProductNotFoundException;
    OrderResponseDto getOrderDetails(Long id);
}
