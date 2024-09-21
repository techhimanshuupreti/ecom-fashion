package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.subcategory.dto.response.SubCategoryResponse;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubCategoryUtils {

    public static SubCategoryResponse convert(SubCategory subCategory) {
        if (ObjectUtils.isEmpty(subCategory)) {
            return new SubCategoryResponse();
        }
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .categoryId(ObjectUtils.isEmpty(subCategory.getCategory().getId()) ? null : subCategory.getCategory().getId())
                .categoryName(ObjectUtils.isEmpty(subCategory.getCategory().getName()) ? "" : subCategory.getCategory().getName())
                .build();
    }

    public static List<SubCategoryResponse> convert(List<SubCategory> subCategories) {

        if (ObjectUtils.isEmpty(subCategories)) {
            return new ArrayList<>();
        }
        return subCategories.stream().map(SubCategoryUtils::convert)
                .collect(Collectors.toList());
    }

    public static PageableResponse<SubCategoryResponse> convert(Page<SubCategory> subCategoryPage) {

        PageableResponse<SubCategoryResponse> pageableResponse = new PageableResponse<>();
        pageableResponse.setCurrentPage(subCategoryPage.getTotalPages() == 0 ? 0 : subCategoryPage.getNumber() + 1);
        pageableResponse.setTotalPages(subCategoryPage.getTotalPages());
        pageableResponse.setTotalElements(subCategoryPage.getNumberOfElements());
        pageableResponse.setData(convert(subCategoryPage.getContent()));
        return pageableResponse;
    }
}
