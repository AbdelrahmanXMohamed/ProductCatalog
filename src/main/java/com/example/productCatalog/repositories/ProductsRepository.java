package com.example.productCatalog.repositories;

import com.example.productCatalog.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {
    @Query("select p  from Products p where p.status=true")
    List<Products> findAll();
//        List<Products> retrievingAllProductsOrderedByPopularity();
}
