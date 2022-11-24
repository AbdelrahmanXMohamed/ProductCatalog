package com.example.productCatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {
    @Size(min=1,message = "This field can't be empty")
    @NotNull(message = "This field can't be null")
    private String name;
}
