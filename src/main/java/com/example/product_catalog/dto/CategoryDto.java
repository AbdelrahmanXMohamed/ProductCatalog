package com.example.product_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    @NotNull(message = "This field is required")
    private Long id;
    @NotBlank(message = "This field can't be empty or null")
    private String name;
}
