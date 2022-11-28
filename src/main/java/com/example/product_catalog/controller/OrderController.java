package com.example.product_catalog.controller;

import com.example.product_catalog.dto.OrderRequestDto;
import com.example.product_catalog.dto.OrderResponseDto;
import com.example.product_catalog.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping
    public OrderResponseDto buyProducts(@RequestBody @Valid OrderRequestDto orderRequestDto)
    {
        return orderService.buyProducts(orderRequestDto);
    }
    @GetMapping("{id}")
    public OrderResponseDto getOrderDetails(@PathVariable Long id)
    {
        return orderService.getOrderDetails(id);
    }
}
