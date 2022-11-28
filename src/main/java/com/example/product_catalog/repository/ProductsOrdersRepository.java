package com.example.product_catalog.repository;

import com.example.product_catalog.entity.ProductsOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsOrdersRepository extends JpaRepository<ProductsOrders,Long> {
}
