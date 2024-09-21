package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryUtils {

    public static CategoryResponse convert(Category category) {

        if (ObjectUtils.isEmpty(category))
            return new CategoryResponse();

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static List<CategoryResponse> convert(List<Category> categories) {

        if(ObjectUtils.isEmpty(categories)){
            return new ArrayList<>();
        }
        return categories.stream().map(CategoryUtils::convert)
                .collect(Collectors.toList());
    }

    public static PageableResponse<CategoryResponse> convert(Page<Category> categoryPage) {

        PageableResponse<CategoryResponse> pageableResponse = new PageableResponse<>();
        pageableResponse.setCurrentPage(categoryPage.getTotalPages() == 0 ? 0 : categoryPage.getNumber() + 1);
        pageableResponse.setTotalPages(categoryPage.getTotalPages());
        pageableResponse.setTotalElements(categoryPage.getNumberOfElements());
        pageableResponse.setData(convert(categoryPage.getContent()));
        return pageableResponse;
    }
}
