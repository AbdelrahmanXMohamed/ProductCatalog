package com.example.product_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCategory extends ProductDto{
    @NotNull(message = "This field is required")
    private Long categoryId;
}
