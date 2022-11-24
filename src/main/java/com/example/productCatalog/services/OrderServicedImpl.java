package com.example.productCatalog.services;

import com.example.productCatalog.dto.*;
import com.example.productCatalog.entities.Orders;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.entities.ProductsOrders;
import com.example.productCatalog.mappers.OrderMapper;
import com.example.productCatalog.mappers.ProductMapper;
import com.example.productCatalog.mappers.ProductOrderMapper;
import com.example.productCatalog.repositories.OrdersRepository;
import com.example.productCatalog.repositories.ProductsOrdersRepository;
import com.example.productCatalog.repositories.ProductsRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServicedImpl implements OrderService {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    ProductsOrdersRepository productsOrdersRepository;
    private ProductMapper productMapper=Mappers.getMapper(ProductMapper.class);
    @Override
    public OrderReturnDto buyListOfProducts(OrderDto orderDto) {
        Orders order=ordersRepository.save(new Orders());
        List<ProductsOrders> productsOrders = new ArrayList<>();
        List<Products> orderedProduct=new ArrayList<>();

        for (int i =0 ; i < orderDto.getProducts().size() ;i++) {
            Products product=productsRepository.getReferenceById(
                    orderDto
                                .getProducts()
                                .get(i)
                                .getProductsId());

            orderedProduct.add(product);

            ProductsOrders productsOrdersToAdd=new ProductsOrders();
            productsOrdersToAdd.setOrders(order);
            productsOrdersToAdd.setProducts(product);
            productsOrdersToAdd.setOrderedQuantity(
                    orderDto
                            .getProducts()
                            .get(i)
                            .getOrderedQuantity()
            );
            productsOrdersRepository.save(productsOrdersToAdd);
            productsOrders.add(productsOrdersToAdd);
        }
        order.setProductsOrders(productsOrders);
        ordersRepository.save(order);
        for (Products products: orderedProduct) {
            products.getProductsOrders().addAll(productsOrders);
            productsRepository.save(products);
        }
        OrderReturnDto returnDto=new OrderReturnDto();
        returnDto.setOrderId(order.getId());
        returnDto.setProducts(
                orderedProduct.stream()
                            .map(products -> productMapper
                                    .productsTProductWithIdDto(products))
                            .collect(Collectors.toList()));
        return returnDto;
    }
    @Override
    public List<OrderReturnDto> getOrderDetails() {
        List<Orders> orders =ordersRepository.findAll();
        List<OrderReturnDto> returnDtos=new ArrayList<>();
        for (int i =0 ;i < orders.size();i++) {
            List<ProductWithIdDto> products=
                    orders.get(i)
                            .getProductsOrders()
                            .stream()
                            .map(productsOrders1 -> productMapper
                                    .productsTProductWithIdDto(productsOrders1.getProducts())
                            ).collect(Collectors.toList());
            returnDtos.add(new OrderReturnDto(orders.get(i).getId(),products));
        }

        return returnDtos;
    }
}
