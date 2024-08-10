package com.devil.ecomfashion.modules.subcategory.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryDTO {

    @NotBlank(message = "Sub-Category name is not blank")
    private String name;

    @NotBlank(message = "Category name is not blank")
    private String categoryName;

}
