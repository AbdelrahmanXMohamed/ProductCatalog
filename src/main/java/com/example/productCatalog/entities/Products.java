package com.example.productCatalog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    public Products(String nameEn, String nameAr, Double price, Integer quantity) {
        this.nameEn = nameEn;
        this.nameAr = nameAr;
        this.price = price;
        this.quantity = quantity;
    }
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
