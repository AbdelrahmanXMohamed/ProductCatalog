package com.example.productCatalog.repositories;

import com.example.productCatalog.entities.ProductsOrders;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsOrdersRepository extends JpaRepository<ProductsOrders,Long> {
//    public List<ProductsOrders> findByOrderId(Long id);

}
