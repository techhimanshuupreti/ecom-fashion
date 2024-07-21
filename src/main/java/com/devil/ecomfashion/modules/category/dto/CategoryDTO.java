package com.devil.ecomfashion.modules.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    // Todo: it defines which like Mobile, Laptop (type: Electronics)
    @NotNull(message = "Category name is not null")
    @NotEmpty(message = "Category name is not empty")
    @NotBlank(message = "Category name is not blank")
    private String name;

}
