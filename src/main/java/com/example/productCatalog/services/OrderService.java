package com.example.productCatalog.services;

import com.example.productCatalog.dto.OrderDto;
import com.example.productCatalog.dto.OrderReturnDto;
import com.example.productCatalog.entities.Orders;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.entities.ProductsOrders;

import java.util.List;

public interface OrderService {
    OrderReturnDto buyListOfProducts(OrderDto orderDto);
    List<OrderReturnDto>  getOrderDetails();
}
