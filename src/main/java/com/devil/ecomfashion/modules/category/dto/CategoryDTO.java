package com.devil.ecomfashion.modules.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryDTO {

    // Todo: it defines which like Mobile, Laptop (type: Electronics)
    @NotNull(message = "Category name is not null")
    @NotEmpty(message = "Category name is not empty")
    @NotBlank(message = "Category name is not blank")
    private String name;

    // Todo: it defines which type of products like Electronics, Clothes,etc
    @NotNull(message = "Category type is not null")
    @NotEmpty(message = "Category type is not empty")
    @NotBlank(message = "Category type is not blank")
    private String type;

}
