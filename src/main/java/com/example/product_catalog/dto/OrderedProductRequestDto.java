package com.example.product_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  OrderedProductRequestDto {
    @NotNull(message = "This field can't be null")
    private Long productId;
    @Positive(message = "This field must be positive")
    @NotNull(message = "This field can't be null")
    private Integer orderedQuantity;
}
