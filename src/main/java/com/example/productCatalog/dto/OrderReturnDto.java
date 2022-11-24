package com.example.productCatalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReturnDto {
    private Long orderId;
    private List<ProductWithIdDto> products;

}
