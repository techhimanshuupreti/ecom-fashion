package com.devil.ecomfashion.modules.cart.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {

    @Min(value = 0, message = "Qty is greater than equal to 1.")
    private int qty;

    @NotNull(message = "Product is required!")
    private Long productId;
}
