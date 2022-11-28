package com.example.product_catalog.repository;

import com.example.product_catalog.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    Optional<Categories> findByName(String name);
}
