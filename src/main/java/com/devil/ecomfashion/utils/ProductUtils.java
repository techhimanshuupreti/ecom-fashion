package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.entiry.Product;
import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductUtils {

    public static ProductResponse convertProductResponse(Product product) {

        if(ObjectUtils.isEmpty(product)) {
            return new ProductResponse();
        }

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .category(SubCategoryUtils.convertSubCategoryResponse(product.getSubCategory()))
                .build();
    }

    public static List<ProductResponse> convertProductResponse(List<Product> products) {

        if(ObjectUtils.isEmpty(products)) {
            return new ArrayList<>();
        }

        return products.stream().map(product -> ProductResponse.builder()
                        .name(product.getName())
                        .category(SubCategoryUtils.convertSubCategoryResponse(product.getSubCategory()))
                        .id(product.getId()).build())
                .collect(Collectors.toList());
    }
}