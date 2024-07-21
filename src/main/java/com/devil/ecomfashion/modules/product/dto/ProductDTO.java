package com.devil.ecomfashion.modules.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

    @NotNull(message = "Product name is not null")
    @NotEmpty(message = "Product name is not empty")
    @NotBlank(message = "Product name is not blank")
    private String name;

    @NotNull(message = "Product category is not null")
    private Long categoryId;

    @NotNull(message = "Product short description is not null")
    @NotEmpty(message = "Product short description is not empty")
    @NotBlank(message = "Product short description is not blank")
    private String shortDescription;

    @NotNull(message = "Product long description is not null")
    @NotEmpty(message = "Product long description is not empty")
    @NotBlank(message = "Product long description is not blank")
    private String longDescription;

    @NotNull(message = "Product Image is not empty")
    private MultipartFile file;

}
