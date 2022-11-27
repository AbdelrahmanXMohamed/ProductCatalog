package com.example.productCatalog.controllers;

import com.example.productCatalog.dtos.OrderDto;
import com.example.productCatalog.dtos.OrderReturnDto;
import com.example.productCatalog.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping
    public OrderReturnDto buyListOfProducts(@RequestBody @Valid OrderDto orderDto)
    {
        return orderService.buyListOfProducts(orderDto);
    }
//    @GetMapping
//    public List<OrderReturnDto> getAllOrderDetails()
//    {
//        return orderService.getOrderDetails();
//    }
    @GetMapping("{id}")
    public OrderReturnDto getOrderDetails(@PathVariable Long id)
    {
        return orderService.getOrderDetails(id);
    }
}
