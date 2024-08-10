package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;

import java.util.List;
import java.util.stream.Collectors;

public class SubCategoryUtils {

    public static SubCategoryResponse convertSubCategoryResponse(SubCategory subCategory) {
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .category(CategoryUtils.convertCategoryResponse(subCategory.getCategory()))
                .build();
    }

    public static List<SubCategoryResponse> convertSubCategoryResponse(List<SubCategory> subCategories) {

        return subCategories.stream().map(subCategory -> SubCategoryResponse.builder()
                        .name(subCategory.getName())
                        .id(subCategory.getId())
                        .category(CategoryUtils.convertCategoryResponse(subCategory.getCategory()))
                        .build())
                .collect(Collectors.toList());
    }
}
