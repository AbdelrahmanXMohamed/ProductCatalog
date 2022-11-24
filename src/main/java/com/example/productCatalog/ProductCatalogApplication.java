package com.example.productCatalog;

import com.example.productCatalog.repositories.CategoriesRepository;
import com.example.productCatalog.repositories.OrdersRepository;
import com.example.productCatalog.repositories.ProductsOrdersRepository;
import com.example.productCatalog.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductCatalogApplication {

	@Autowired
	ProductsRepository productsRepository;
	@Autowired
	OrdersRepository ordersRepository;
	@Autowired
	ProductsOrdersRepository productsOrdersRepository;
	@Autowired
	CategoriesRepository categoriesRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}
//	@Bean
//	CommandLineRunner runner(){
//		return args -> {
//			Categories toy=new Categories();
//			toy.setName("Toy");
//			Categories cloth=new Categories();
//			cloth.setName("Cloth");
//			categoriespRepository.saveAll(List.of(toy,cloth));
//
//			Products product1=new Products("t-shirt","تي شيرت",12.5,5);
//			Products product2=new Products("ball","كرة",0.01,99);
//			Products product3=new Products("Jacket","جاكيت",82.0,45);
//			Products product4=new Products("toy","لعبة",50.5,99);
//			product1.setCategory(cloth);
//			product2.setCategory(toy);
//			product3.setCategory(cloth);
//			product4.setCategory(toy);
//
//			productsRepository.saveAll(List.of(product1,product2,product3,product4));
//			Orders order1 =new Orders();
//			Orders order2 =new Orders();
//			ordersRepository.saveAll(List.of(order1,order2));
//			ProductsOrders productsOrders1= new ProductsOrders();
//			productsOrders1.setProducts(product1);
//			productsOrders1.setOrders(order1);
//			ProductsOrders productsOrders2= new ProductsOrders();
//			productsOrders2.setProducts(product2);
//			productsOrders2.setOrders(order1);
//			ProductsOrders productsOrders3= new ProductsOrders();
//			productsOrders3.setProducts(product3);
//			productsOrders3.setOrders(order2);
//			ProductsOrders productsOrders4= new ProductsOrders();
//			productsOrders4.setProducts(product4);
//			productsOrders4.setOrders(order2);
//			productsOrdersRepository
//					.saveAll(
//							List.of(productsOrders1,productsOrders2,productsOrders3,productsOrders4)
//					);
//			product1.setProductsOrders(List.of(productsOrders1));
//			product2.setProductsOrders(List.of(productsOrders2));
//			product3.setProductsOrders(List.of(productsOrders3));
//			product4.setProductsOrders(List.of(productsOrders4));
//			productsRepository.saveAll(List.of(product1,product2,product3,product4));
//			order1.setProductsOrders(List.of(productsOrders1,productsOrders2));
//			order2.setProductsOrders(List.of(productsOrders3,productsOrders4));
//			ordersRepository.saveAll(List.of(order1,order2));
//		};
//	}
}
