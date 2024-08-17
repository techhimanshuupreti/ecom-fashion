package com.devil.ecomfashion.modules.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Double price = 0.00;
    private Long subCategoryId;
    private String subCategoryName;
    private Long categoryId;
    private String categoryName;
    private String description;
}