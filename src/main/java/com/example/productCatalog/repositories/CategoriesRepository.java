package com.example.productCatalog.repositories;

import com.example.productCatalog.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    Categories findByName(String name);
}
