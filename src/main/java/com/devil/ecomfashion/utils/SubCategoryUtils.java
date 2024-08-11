package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubCategoryUtils {

    public static SubCategoryResponse convertSubCategoryResponse(SubCategory subCategory) {
        if(ObjectUtils.isEmpty(subCategory)){
            return new SubCategoryResponse();
        }
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .category(CategoryUtils.convertCategoryResponse(subCategory.getCategory()))
                .build();
    }

    public static List<SubCategoryResponse> convertSubCategoryResponse(List<SubCategory> subCategories) {

        if(ObjectUtils.isEmpty(subCategories)){
            return new ArrayList<>();
        }
        return subCategories.stream().map(subCategory -> SubCategoryResponse.builder()
                        .name(subCategory.getName())
                        .id(subCategory.getId())
                        .category(CategoryUtils.convertCategoryResponse(subCategory.getCategory()))
                        .build())
                .collect(Collectors.toList());
    }
}
