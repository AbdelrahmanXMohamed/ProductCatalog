package com.example.productCatalog.dto;

import com.example.productCatalog.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String nameEn;
    private String nameAr;
    private Double price;
    private Integer quantity;
}
