package com.example.productCatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderDto {
    private Long productsId;
    private Integer orderedQuantity=1;
}
