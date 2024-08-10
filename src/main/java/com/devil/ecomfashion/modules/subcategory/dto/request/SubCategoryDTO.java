package com.devil.ecomfashion.modules.subcategory.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryDTO {

    @NotNull(message = "Sub-Category name is not null")
    @NotEmpty(message = "Sub-Category name is not empty")
    @NotBlank(message = "Sub-Category name is not blank")
    private String name;

    @NotNull(message = "Category name is not null")
    @NotEmpty(message = "Category name is not empty")
    @NotBlank(message = "Category name is not blank")
    private String categoryName;

}
