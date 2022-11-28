package com.example.product_catalog;

import com.example.product_catalog.repository.CategoriesRepository;
import com.example.product_catalog.repository.OrdersRepository;
import com.example.product_catalog.repository.ProductsOrdersRepository;
import com.example.product_catalog.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}
}
