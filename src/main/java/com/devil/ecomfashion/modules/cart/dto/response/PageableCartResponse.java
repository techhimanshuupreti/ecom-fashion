package com.devil.ecomfashion.modules.cart.dto.response;

import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageableCartResponse {

    private List<CartResponse> data;
    private int totalPages;
    private int totalElements;
    private int currentPage;

}