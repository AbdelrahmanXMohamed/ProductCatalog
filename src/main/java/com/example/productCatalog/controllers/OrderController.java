package com.example.productCatalog.controllers;

import com.example.productCatalog.dto.OrderDto;
import com.example.productCatalog.dto.OrderReturnDto;
import com.example.productCatalog.entities.Orders;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.entities.ProductsOrders;
import com.example.productCatalog.repositories.OrdersRepository;
import com.example.productCatalog.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping
    public OrderReturnDto buyListOfProducts(@RequestBody OrderDto orderDto)
    {
        return orderService.buyListOfProducts(orderDto);
    }
    @GetMapping
    public List<OrderReturnDto> getOrderDetails()
    {
        return orderService.getOrderDetails();
    }
}
