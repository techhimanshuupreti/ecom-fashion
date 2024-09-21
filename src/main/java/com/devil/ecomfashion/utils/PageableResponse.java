package com.devil.ecomfashion.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponse<T>  {
    private List<T> data;
    private int totalPages;
    private int totalElements;
    private int currentPage;

}
