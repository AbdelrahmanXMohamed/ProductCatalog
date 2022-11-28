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
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryId")
    private Long id;
    @Column(unique = true)
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Products> productItems;
}
