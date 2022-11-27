package com.example.productCatalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto extends ProductDto{
    @NotNull(message = "This field is required")
    private Long id;
    @Valid
    private CategoryDto category;

}
