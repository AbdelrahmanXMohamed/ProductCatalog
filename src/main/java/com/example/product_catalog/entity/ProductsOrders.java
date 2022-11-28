package com.example.product_catalog.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ProductsOrdersId")
    private Long id;
    private Integer orderedQuantity;
    private Double price;
    private Double totalPrice;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="productId")
    private Products product;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="orderId")
    private Orders order;

}
