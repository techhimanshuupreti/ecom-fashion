package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.entiry.Product;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductUtils {

    public static ProductResponse convert(Product product) {

        if (ObjectUtils.isEmpty(product)) {
            return new ProductResponse();
        }

        return ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .stock(product.getStock())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(ObjectUtils.isEmpty(product.getSubCategory().getCategory().getId()) ? null : product.getSubCategory().getCategory().getId())
                .categoryName(ObjectUtils.isEmpty(product.getSubCategory().getCategory().getName()) ? "" : product.getSubCategory().getCategory().getName())
                .subCategoryId(ObjectUtils.isEmpty(product.getSubCategory().getId()) ? null : product.getSubCategory().getId())
                .subCategoryName(ObjectUtils.isEmpty(product.getSubCategory().getName()) ? "" : product.getSubCategory().getName())
                .build();
    }

    public static List<ProductResponse> convert(List<Product> products) {

        if (ObjectUtils.isEmpty(products)) {
            return new ArrayList<>();
        }

        return products.stream().map(ProductUtils::convert)
                .collect(Collectors.toList());
    }

    public static PageableResponse<ProductResponse> convert(Page<Product> productPage) {

        PageableResponse<ProductResponse> pageableResponse = new PageableResponse<>();
        pageableResponse.setCurrentPage(productPage.getTotalPages() == 0 ? 0 : productPage.getNumber() + 1);
        pageableResponse.setTotalPages(productPage.getTotalPages());
        pageableResponse.setTotalElements(productPage.getNumberOfElements());
        pageableResponse.setData(convert(productPage.getContent()));
        return pageableResponse;
    }
}
