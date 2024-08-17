package com.devil.ecomfashion.modules.order.dto.request;

import com.devil.ecomfashion.constant.Message;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @Min(value = 0, message = Message.QTY_RANGE_REQUIRED)
    private int qty;

    @NotNull(message = Message.PRODUCT_REQUIRED)
    private Long productId;


}
