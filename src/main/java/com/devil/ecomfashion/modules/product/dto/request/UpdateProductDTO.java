package com.devil.ecomfashion.modules.product.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateProductDTO {

    private String name;

    private Long subcategoryId;

    private String description;

    private MultipartFile file;

    @Min(value = 0, message = "Product price is less than equal to 0!")
    private Double price;

}