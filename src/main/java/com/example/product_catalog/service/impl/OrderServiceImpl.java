package com.example.product_catalog.service.impl;

import com.example.product_catalog.dto.*;
import com.example.product_catalog.entity.Orders;
import com.example.product_catalog.entity.Products;
import com.example.product_catalog.entity.ProductsOrders;
import com.example.product_catalog.entity.Users;
import com.example.product_catalog.exception.*;
import com.example.product_catalog.mapper.OrderMapper;
import com.example.product_catalog.mapper.ProductMapper;
import com.example.product_catalog.repository.OrdersRepository;
import com.example.product_catalog.repository.ProductsOrdersRepository;
import com.example.product_catalog.repository.ProductsRepository;
import com.example.product_catalog.repository.UsersRepository;
import com.example.product_catalog.service.OrderService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ProductsOrdersRepository productsOrdersRepository;
    @Autowired
    UsersRepository usersRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    private final OrderMapper orderMapper=Mappers.getMapper(OrderMapper.class);
    @Override
    public OrderResponseDto buyProducts(OrderRequestDto orderRequestDto){
        Users currentLoginUser = ((Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<ProductsOrders> orderedProducts = new ArrayList<>();
        buildOrderProDucts(currentLoginUser,orderRequestDto, orderedProducts);

        Orders order=new Orders();
        order.setTotalPrice(calculateOrderTotalPrice(orderedProducts));
        order.setUser(currentLoginUser);
        Orders ordered = ordersRepository.save(order);

        orderedProducts.forEach(orderedProduct -> orderedProduct.setOrder(ordered));
        productsOrdersRepository.saveAll(orderedProducts);
        orderedProducts.forEach(OrderServiceImpl::orderedProductQuantity);

        orderedProducts.forEach(orderedProduct -> productsRepository.save(orderedProduct.getProduct()));
        OrderResponseDto returnDto = getOrderResponseDto(currentLoginUser, orderedProducts, order, ordered);
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
        Users currentLoginUser = ((Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        if (!orders.isPresent()) {
            throw new OrderNotFoundException(id);
        }
        if (!orders.get().getUser().getId().equals(currentLoginUser.getId()))
        {
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
        orderResponseDto.setCustomerName(order.getUser().getUsername());
        orderResponseDto.setCustomerMail(order.getUser().getEmail());

        return orderResponseDto;
    }
    @Override
    public List<OrderResponseDto> getOrdersDetails() {
        Users currentLoginUser = ((Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return currentLoginUser
                .getOrdersList()
                .stream()
                .map(this::getOrderResponseDto)
                .collect(Collectors.toList());
    }
    private OrderResponseDto getOrderResponseDto(Orders order) {
        OrderResponseDto orderResponseDto = orderMapper.orderToOrderReturnDto(order);
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto
                .setProducts(
                        order
                        .getOrderedProducts().stream()
                        .map(orderedProduct ->
                                {
                                  OrderedProductDto orderedProductDto= productMapper
                                            .productsToOrderedProductDto(orderedProduct.getProduct());
                                  orderedProductDto.setOrderedQuantity(orderedProduct.getOrderedQuantity());
                                  orderedProductDto.setTotalPrice(orderedProduct.getTotalPrice());
                                  return orderedProductDto;
                                }
                                )
                        .collect(Collectors.toList())
                        );

        orderResponseDto.setCustomerMail(order.getUser().getEmail());
        orderResponseDto.setCustomerName(order.getUser().getUsername());
        return orderResponseDto;
    }
    private OrderResponseDto getOrderResponseDto(Users currentLoginUser, List<ProductsOrders> orderedProducts, Orders order, Orders ordered) {
        OrderResponseDto returnDto = new OrderResponseDto();
        returnDto.setOrderId(order.getId());
        returnDto.setProducts(orderedProducts.stream().map(setProductDetails()).collect(Collectors.toList()));
        returnDto.setTotalPrice(ordered.getTotalPrice());
        returnDto.setCustomerName(currentLoginUser.getUsername());
        returnDto.setCustomerMail(currentLoginUser.getEmail());
        return returnDto;
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
    private void buildOrderProDucts(Users currentLoginUser,OrderRequestDto orderRequestDto, List<ProductsOrders> orderedProducts) {
        orderRequestDto.getOrderItems().forEach(
                item -> {
                    Optional<Products> product =  productsRepository.findById(item.getProductId());
                    if(product.isPresent()){
                        int sum= getSumOfPreviousOrder(currentLoginUser, product);
                        int productOrderedLimit=product.get().getOrderedLimit();
                        if (sum+item.getOrderedQuantity()>productOrderedLimit)
                        {
                            throw new OrderedProductMoreThanOrderLimitException(product.get().getId(),productOrderedLimit,item.getOrderedQuantity(),sum);
                        }
                        ProductsOrders productsOrder = new ProductsOrders();
                        orderedProducts.add(productsOrder);
                        productsOrder.setProduct(product.get());
                        productsOrder.setOrderedQuantity(item.getOrderedQuantity());
                        productsOrder.setPrice(product.get().getPrice());
                        productsOrder.setTotalPrice(calculateItemTotalPrice(product.get().getPrice() , item.getOrderedQuantity()));
                    }
                    else {
                        throw new ProductNotFoundException(item.getProductId());
                    }
                }
        );
    }
    private static int getSumOfPreviousOrder(Users currentLoginUser, Optional<Products> product) {
        return product.get().getOrderedProducts().stream().mapToInt(orderedProduct ->
                {
                    if (orderedProduct.getOrder().getUser().getId().equals(currentLoginUser.getId())) {
                        return orderedProduct.getOrderedQuantity();
                    }
                    return 0;
                }).sum();
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
    private static void orderedProductQuantity(ProductsOrders orderedProduct) {
        int availableProductQuantity = orderedProduct.getProduct().getQuantity();
        int productOrderedQuantity= orderedProduct.getProduct().getOrderedQuantity();
        if(availableProductQuantity ==productOrderedQuantity) {
            throw new ProductOutOfStockException(orderedProduct.getProduct().getId());
        }
        int totalOrderedProductQuantity=calculateOrderedProductQuantity(orderedProduct);
        if(totalOrderedProductQuantity> availableProductQuantity) {
            throw new OrderedProductGreaterThanAvailableQuantityException
                    (orderedProduct.getProduct().getId(), availableProductQuantity,orderedProduct.getOrderedQuantity());
        }
        orderedProduct.getProduct().setOrderedQuantity(totalOrderedProductQuantity);
        orderedProduct.getProduct().setQuantity(orderedProduct.getProduct().getQuantity()-orderedProduct.getOrderedQuantity());
    }
    private static int calculateOrderedProductQuantity(ProductsOrders orderedProduct) {
        return orderedProduct.getProduct().getOrderedQuantity() + orderedProduct.getOrderedQuantity();
    }
}
