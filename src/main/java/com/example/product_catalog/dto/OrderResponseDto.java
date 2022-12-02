package com.example.product_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    @NotNull(message = "This field is required")
    private Long orderId;
    @Valid
    private List<OrderedProductDto> products;
    private Double totalPrice;
    private String customerName;
    private String customerMail;

}
