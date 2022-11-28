package com.example.product_catalog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long id;
    @Column
    private String nameEn;
    @Column
    private String nameAr;
    @Column
    private Double price;
    private Boolean status=Boolean.TRUE;
    private Integer orderedQuantity=0;
    @Column
    private Integer quantity;
    @JsonManagedReference
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductsOrders> orderedProducts;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Categories category;


}
