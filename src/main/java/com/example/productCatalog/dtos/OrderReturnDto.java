package com.example.productCatalog.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReturnDto {
    @NotNull(message = "This field is required")
    private Long orderId;
    @Valid
    private List<OrderedProductDto> products;
    private Double totalPrice;

}
