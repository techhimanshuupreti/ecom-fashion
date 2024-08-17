package com.devil.ecomfashion.modules.category.dto.request;

import com.devil.ecomfashion.constant.Message;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = Message.NAME_REQUIRED)
    private String name;

}
