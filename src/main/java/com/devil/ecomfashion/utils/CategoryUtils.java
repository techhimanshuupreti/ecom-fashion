package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryUtils {

    public static CategoryResponse convertCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .subCategories(SubCategoryUtils.convertSubCategoryResponse(category.getSubCategories()))
                .build();
    }

    public static List<CategoryResponse> convertCategoryResponse(List<Category> categories) {

        return categories.stream().map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .subCategories(SubCategoryUtils.convertSubCategoryResponse(category.getSubCategories()))
                        .build())
                .collect(Collectors.toList());
    }
}
