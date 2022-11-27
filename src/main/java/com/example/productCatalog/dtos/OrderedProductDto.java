package com.example.productCatalog.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductDto {
    private Long id;
    private String nameEn;

    private String nameAr;
    @Positive(message = "This field must be positive")
    @NotNull(message = "This field can't be null")
    private Integer orderedQuantity;
    @Positive(message = "This field must be positive")
    @NotNull(message = "This field can't be null")
    private Double price;
    @Positive(message = "This field must be positive")
    @NotNull(message = "This field can't be null")
    private Double totalPrice;
}
