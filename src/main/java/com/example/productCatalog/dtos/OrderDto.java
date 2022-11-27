package com.example.productCatalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @Valid
    private List<ProductOrderDto> products;

}
