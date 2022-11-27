package com.example.productCatalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {
    @NotNull(message = "This field is required")
    @NotBlank(message = "This field can't be empty")
    private String name;
}
