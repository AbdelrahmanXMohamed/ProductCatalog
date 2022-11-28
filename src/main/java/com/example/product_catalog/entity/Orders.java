package com.example.product_catalog.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;
    @Column
    private Double totalPrice;
    @JsonManagedReference
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<ProductsOrders> orderedProducts;
}
