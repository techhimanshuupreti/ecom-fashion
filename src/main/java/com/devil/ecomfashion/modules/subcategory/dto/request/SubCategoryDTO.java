package com.devil.ecomfashion.modules.subcategory.dto.request;

import com.devil.ecomfashion.constant.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryDTO {

    @NotBlank(message = Message.NAME_REQUIRED)
    private String name;

    @NotBlank(message = Message.CATEGORY_REQUIRED)
    private String categoryName;

}
