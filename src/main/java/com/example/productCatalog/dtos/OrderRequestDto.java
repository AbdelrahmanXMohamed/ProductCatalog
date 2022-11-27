package com.example.productCatalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @Valid
    private List<OrderedProductRequestDto> orderItems;

}