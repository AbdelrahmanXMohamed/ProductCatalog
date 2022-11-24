package com.example.productCatalog.dto;

import com.example.productCatalog.entities.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private List<ProductOrderDto> products;

}
