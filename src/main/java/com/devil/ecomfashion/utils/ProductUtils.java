package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.product.dto.response.PageableProductResponse;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.entiry.Product;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductUtils {

    public static ProductResponse convertProductResponse(Product product) {

        if (ObjectUtils.isEmpty(product)) {
            return new ProductResponse();
        }

        return ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .subCategory(SubCategoryUtils.convertSubCategoryResponse(product.getSubCategory()))
                .build();
    }

    public static List<ProductResponse> convertProductResponse(List<Product> products) {

        if (ObjectUtils.isEmpty(products)) {
            return new ArrayList<>();
        }

        return products.stream().map(product -> ProductResponse.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .subCategory(SubCategoryUtils.convertSubCategoryResponse(product.getSubCategory()))
                        .id(product.getId()).build())
                .collect(Collectors.toList());
    }

    public static PageableProductResponse convertProductResponse(Page<Product> productPage) {

        if (ObjectUtils.isEmpty(productPage)) {
            return PageableProductResponse.builder()
                    .data(new ArrayList<>()).build();
        }

        List<ProductResponse> products = productPage.getContent().stream().map(product -> ProductResponse.builder()
                        .name(product.getName())
                        .price(product.getPrice())
                        .subCategory(SubCategoryUtils.convertSubCategoryResponse(product.getSubCategory()))
                        .id(product.getId()).build())
                .toList();

        return PageableProductResponse.builder()
                .currentPage(productPage.getNumber() + 1)
                .totalPages(productPage.getTotalPages())
                .totalElements(productPage.getNumberOfElements())
                .data(products).build();
    }
}
