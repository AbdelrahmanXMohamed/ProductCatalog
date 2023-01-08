package com.example.product_catalog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class
OrderedProductDto {
    @NotNull(message = "This field can't be null")
    private Long id;
    @NotBlank(message = "This field can't be empty or null")
    private String nameEn;
    @NotBlank(message = "This field can't be empty or null")
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
    private String image;
}
