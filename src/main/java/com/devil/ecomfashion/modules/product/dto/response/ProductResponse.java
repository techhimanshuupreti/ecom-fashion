package com.devil.ecomfashion.modules.product.dto.response;

import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private Long id;
    private String name;
    private Double price = 0.00;
    private SubCategoryResponse subCategory;
}