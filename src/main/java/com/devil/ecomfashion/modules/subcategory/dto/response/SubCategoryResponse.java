package com.devil.ecomfashion.modules.subcategory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryResponse {

    private Long id;
    private String name;
    private Long categoryId;
    private String categoryName;

}
