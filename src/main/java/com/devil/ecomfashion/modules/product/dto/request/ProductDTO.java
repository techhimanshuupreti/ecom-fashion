package com.devil.ecomfashion.modules.product.dto.request;

import jakarta.validation.constraints.Min;
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

    @NotBlank(message = "Product name is required!")
    private String name;

    @NotNull(message = "Product Sub category is required!")
    private Long subcategoryId;

    @NotBlank(message = "Product description is required!")
    private String description;

    @NotNull(message = "Product Image is required!")
    private MultipartFile file;

    @NotNull(message = "Product price is required!")
    @Min(value = 0, message = "Product price is less than equal to 0!")
    private Double price;

}
