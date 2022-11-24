package com.example.productCatalog.dto;

import com.example.productCatalog.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithIdDto extends ProductDto{
    private Long id;
    private CategoryDto category;

}
