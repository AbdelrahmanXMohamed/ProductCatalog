package com.example.productCatalog.services;

import com.example.productCatalog.dtos.*;
import com.example.productCatalog.entities.Orders;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.entities.ProductsOrders;
import com.example.productCatalog.excaptions.OrderNotFoundException;
import com.example.productCatalog.excaptions.ProductNotFoundException;
import com.example.productCatalog.mappers.OrderMapper;
import com.example.productCatalog.mappers.ProductMapper;
import com.example.productCatalog.repositories.OrdersRepository;
import com.example.productCatalog.repositories.ProductsOrdersRepository;
import com.example.productCatalog.repositories.ProductsRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServicedImpl implements OrderService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ProductsOrdersRepository productsOrdersRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Override
    public OrderReturnDto buyListOfProducts(OrderDto orderDto) {
        List<ProductsOrders> orderedProduct = new ArrayList<>();
        double totalPrice=0.0;
        // Product
        for (int i = 0; i < orderDto.getProducts().size(); i++) {

            Optional<Products> products = productsRepository
                    .findById(orderDto.getProducts().get(i).getProductsId());

            if (!products.isPresent()) {
                throw new ProductNotFoundException(orderDto.getProducts().get(i).getProductsId());
            }

            orderedProduct.add(new ProductsOrders());
            orderedProduct.get(i).setProducts(products.get());
            orderedProduct.get(i).setOrderedQuantity(
                    orderDto
                            .getProducts()
                            .get(i)
                            .getOrderedQuantity()
            );
            orderedProduct.get(i).setPrice(products.get().getPrice());
            orderedProduct
                    .get(i)
                    .setTotalPrice(
                            products
                                    .get()
                                    .getPrice()*orderDto
                                    .getProducts()
                                    .get(i)
                                    .getOrderedQuantity()
                    );
            totalPrice+=products.get().getPrice()*orderDto.getProducts().get(i).getOrderedQuantity();
        }
        Orders order=new Orders();
        order.setTotalPrice(totalPrice);
        Orders ordered = ordersRepository.save(order);

        orderedProduct.forEach(productsOrders1 -> productsOrders1.setOrders(ordered));
        productsOrdersRepository.saveAll(orderedProduct);

        OrderReturnDto returnDto = new OrderReturnDto();
        returnDto.setOrderId(order.getId());
        returnDto.setProducts(orderedProduct.stream().map(
                        orderedProduct1 -> {
                                OrderedProductDto orderedProductDto = productMapper.productsToOrderedProductDto(
                                                    orderedProduct1.getProducts()
                                            );
                                orderedProductDto.setPrice(orderedProduct1.getPrice());
                                orderedProductDto.setTotalPrice(orderedProduct1.getTotalPrice());
                                return orderedProductDto;
                        })
                .collect(
                        Collectors.toList()
                ));

        returnDto.setTotalPrice(ordered.getTotalPrice());
        for (int i = 0; i < orderDto.getProducts().size(); i++) {
            returnDto
                    .getProducts()
                    .get(i)
                    .setOrderedQuantity(
                            orderDto
                                    .getProducts()
                                    .get(i)
                                    .getOrderedQuantity()
                    );
        }
//        returnDto.getProducts().forEach(System.out::println);
        return returnDto;
    }
    @Override
    public OrderReturnDto getOrderDetails(Long id) {

        Optional<Orders> orders = ordersRepository.findById(id);

        if (!orders.isPresent()) {
            throw new OrderNotFoundException(id);
        }

        OrderReturnDto orderReturnDto = new OrderReturnDto();
        orderReturnDto.setOrderId(id);
        orderReturnDto.setProducts(
                orders
                        .get()
                        .getProductsOrders()
                        .stream()
                        .map(product -> productMapper.productsToOrderedProductDto(product.getProducts()))
                        .collect(Collectors.toList())
        );
        for (int i = 0; i < orders.get().getProductsOrders().size(); i++) {
            orderReturnDto
                    .getProducts()
                    .get(i)
                    .setOrderedQuantity(orders
                            .get()
                            .getProductsOrders()
                            .get(i)
                            .getOrderedQuantity()
                    );
                orderReturnDto
                        .getProducts()
                        .get(i)
                        .setPrice(orders
                                .get()
                                .getProductsOrders()
                                .get(i)
                                .getPrice()
                        );

            orderReturnDto
                    .getProducts()
                    .get(i)
                    .setPrice(orders
                            .get()
                            .getProductsOrders()
                            .get(i)
                            .getPrice()
                    );
            orderReturnDto
                    .getProducts()
                    .get(i)
                    .setTotalPrice(orders
                            .get()
                            .getProductsOrders()
                            .get(i)
                            .getTotalPrice()
                    );
        }
//        orders.get().setTotalPrice(orderReturnDto.getTotalPrice());
        orderReturnDto.setTotalPrice(orders.get().getTotalPrice());
        return orderReturnDto;
    }
}
