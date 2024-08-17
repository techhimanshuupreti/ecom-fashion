package com.devil.ecomfashion.modules.product.dto.request;

import com.devil.ecomfashion.constant.Message;
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

    @NotBlank(message = Message.PRODUCT_REQUIRED)
    private String name;

    @NotNull(message = Message.SUBCATEGORY_REQUIRED)
    private Long subcategoryId;

    @NotBlank(message = Message.PRODUCT_DESCRIPTION_REQUIRED)
    private String description;

    @NotNull(message = Message.PRODUCT_IMAGE_REQUIRED)
    private MultipartFile file;

    @NotNull(message = Message.PRODUCT_PRICE_REQUIRED)
    @Min(value = 0, message = Message.INVALID_PRODUCT_PRICE)
    private Double price;

}
