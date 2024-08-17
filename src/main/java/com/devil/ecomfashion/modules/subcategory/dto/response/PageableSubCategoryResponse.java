package com.devil.ecomfashion.modules.subcategory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableSubCategoryResponse {

    private List<SubCategoryResponse> data;
    private int totalPages;
    private int totalElements;
    private int currentPage;

}
