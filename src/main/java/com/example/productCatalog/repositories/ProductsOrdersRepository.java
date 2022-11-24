package com.example.productCatalog.repositories;

import com.example.productCatalog.entities.ProductsOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsOrdersRepository extends JpaRepository<ProductsOrders,Long> {
}
