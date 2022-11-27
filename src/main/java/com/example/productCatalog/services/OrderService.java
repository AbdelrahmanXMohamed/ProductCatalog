package com.example.productCatalog.services;

import com.example.productCatalog.dtos.OrderRequestDto;
import com.example.productCatalog.dtos.OrderResponseDto;
import com.example.productCatalog.excaptions.ProductNotFoundException;

public interface OrderService {
    OrderResponseDto buyProducts(OrderRequestDto orderRequestDto) throws ProductNotFoundException;
    OrderResponseDto getOrderDetails(Long id);
}
