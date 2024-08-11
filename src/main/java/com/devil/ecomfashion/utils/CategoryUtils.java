package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryUtils {

    public static CategoryResponse convertCategoryResponse(Category category) {

        if (ObjectUtils.isEmpty(category))
            return new CategoryResponse();

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<CategoryResponse> convertCategoryResponse(List<Category> categories) {

        if(ObjectUtils.isEmpty(categories)){
            return new ArrayList<>();
        }
        return categories.stream().map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
