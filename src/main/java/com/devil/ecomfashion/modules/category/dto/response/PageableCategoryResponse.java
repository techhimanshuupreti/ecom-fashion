package com.devil.ecomfashion.modules.category.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableCategoryResponse {

    private List<CategoryResponse> data;
    private int totalPages;
    private int totalElements;
    private int currentPage;
}
