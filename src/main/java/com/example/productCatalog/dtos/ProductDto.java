package com.example.productCatalog.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotNull(message = "This field is required")
    @NotBlank(message = "This can't be empty")
    private String nameEn;

    @NotNull(message = "This field is required")
    @NotBlank(message = "This can't be empty")
    private String nameAr;

    @Positive(message = "This field can't be zero or negative")
    @NotNull(message = "This field is required")
    private Double price;

    @Positive(message = "This field can't be zero or negative")
    @NotNull(message = "This field is required")
    private Integer quantity;
}