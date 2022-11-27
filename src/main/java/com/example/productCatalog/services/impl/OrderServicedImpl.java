package com.example.productCatalog.services.impl;

import com.example.productCatalog.dtos.*;
import com.example.productCatalog.entities.Orders;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.entities.ProductsOrders;
import com.example.productCatalog.excaptions.OrderNotFoundException;
import com.example.productCatalog.mappers.OrderMapper;
import com.example.productCatalog.mappers.ProductMapper;
import com.example.productCatalog.repositories.OrdersRepository;
import com.example.productCatalog.repositories.ProductsOrdersRepository;
import com.example.productCatalog.repositories.ProductsRepository;
import com.example.productCatalog.services.OrderService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
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
    public OrderResponseDto buyProducts(OrderRequestDto orderRequestDto){
        List<ProductsOrders> orderedProducts = new ArrayList<>();
        buildOrderProDucts(orderRequestDto, orderedProducts);

        Orders order=new Orders();
        order.setTotalPrice(calculateOrderTotalPrice(orderedProducts));
        Orders ordered = ordersRepository.save(order);

        orderedProducts.forEach(orderedProduct -> orderedProduct.setOrder(ordered));
        productsOrdersRepository.saveAll(orderedProducts);

        OrderResponseDto returnDto = new OrderResponseDto();
        returnDto.setOrderId(order.getId());
        returnDto.setProducts(orderedProducts.stream().map(setProductDetails()).collect(Collectors.toList()));
        returnDto.setTotalPrice(ordered.getTotalPrice());

        AtomicInteger index= new AtomicInteger();
        returnDto.getProducts()
                .forEach(product->{
                    product.setOrderedQuantity(orderRequestDto.getOrderItems().get(index.get()).getOrderedQuantity());
                    index.incrementAndGet();
                });
        return returnDto;
    }
    @Override
    public OrderResponseDto getOrderDetails(Long id) {
        Optional<Orders> orders = ordersRepository.findById(id);
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        if (!orders.isPresent()) {
            throw new OrderNotFoundException(id);
        }
        Orders order= orders.get();
        orderResponseDto.setOrderId(id);
        orderResponseDto.setProducts(setProductsToOrderResponse(order));
        AtomicInteger index= new AtomicInteger();
        orderResponseDto.getProducts().forEach(product-> {
            setProductDetailsToOrderResponse(order, index, product);
            index.incrementAndGet();
        });
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        return orderResponseDto;
    }
    private Double calculateItemTotalPrice(Double price, Integer orderedQuantity) {
        return price*orderedQuantity;
    }
    private Function<ProductsOrders, OrderedProductDto> setProductDetails() {
        return orderedProduct -> {
            OrderedProductDto orderedProductDto = productMapper
                    .productsToOrderedProductDto(orderedProduct.getProduct());
            orderedProductDto
                    .setPrice(orderedProduct.getPrice());
            orderedProductDto
                    .setTotalPrice(orderedProduct.getTotalPrice());
            return orderedProductDto;
        };
    }
    private Double calculateOrderTotalPrice(List<ProductsOrders> orderedProducts) {
        return orderedProducts.stream()
                .mapToDouble(ProductsOrders::getTotalPrice)
                .sum();
    }
    private void buildOrderProDucts(OrderRequestDto orderRequestDto, List<ProductsOrders> orderedProducts) {
        orderRequestDto.getOrderItems().forEach(
                item -> {
                    Optional<Products> product =  productsRepository.findById(item.getProductsId());
                    if(product.isPresent()){
                        ProductsOrders productsOrder = new ProductsOrders();
                        orderedProducts.add(productsOrder);
                        productsOrder.setProduct(product.get());
                        productsOrder.setOrderedQuantity(item.getOrderedQuantity());
                        productsOrder.setPrice(product.get().getPrice());
                        productsOrder.setTotalPrice(calculateItemTotalPrice(product.get().getPrice() , item.getOrderedQuantity()));
                    }
                }
        );
    }
    private static void setProductDetailsToOrderResponse(Orders order, AtomicInteger index, OrderedProductDto product) {
        product.setOrderedQuantity(order.getOrderedProducts().get(index.get()).getOrderedQuantity());
        product.setPrice(order.getOrderedProducts().get(index.get()).getPrice());
        product.setTotalPrice(order.getOrderedProducts().get(index.get()).getTotalPrice());
    }
    private List<OrderedProductDto> setProductsToOrderResponse(Orders order) {
        return order.getOrderedProducts()
                .stream()
                .map(product -> productMapper.productsToOrderedProductDto(product.getProduct()))
                .collect(Collectors.toList());
    }

}
