package com.devil.ecomfashion.modules.subcategory.dto.response;

import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
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
public class SubCategoryResponse {

    private Long id;
    private String name;
    private CategoryResponse category;

}
