package com.example.product_catalog.dto;

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
