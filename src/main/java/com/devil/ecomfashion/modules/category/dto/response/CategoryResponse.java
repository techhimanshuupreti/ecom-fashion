package com.devil.ecomfashion.modules.category.dto.response;

import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
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
public class CategoryResponse {

    private Long id;
    private String name;
    private List<SubCategoryResponse> subCategories;

}
