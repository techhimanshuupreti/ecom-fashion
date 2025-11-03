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

    @Min(value = 1, message = Message.QTY_RANGE_REQUIRED)
    private int qty;

    @NotNull(message = Message.PRODUCT_REQUIRED)
    @Min(value=1,message = Message.PRODUCT_NOT_FOUND)
    private Long productId;

    @NotNull(message = Message.USER_REQUIRED)
    @Min(value = 1, message = Message.USER_NOT_FOUND)
    private Long userId;
}
