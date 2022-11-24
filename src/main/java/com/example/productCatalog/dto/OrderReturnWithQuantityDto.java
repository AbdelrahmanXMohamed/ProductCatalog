package com.example.productCatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReturnWithQuantityDto extends OrderReturnDto {
    private Integer quantity=1;
}
