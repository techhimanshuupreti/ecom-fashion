package com.devil.ecomfashion.modules.cart.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {

    private long id;

    private int qty;

    private Double totalPrice;

    private String productName;

    private String productDescription;

    private String productImageUrl;

    private Long productId;

    private Double price;

    private String categoryName;

    private String subCategoryName;

    private Long categoryId;

    private Long subCategoryId;
}