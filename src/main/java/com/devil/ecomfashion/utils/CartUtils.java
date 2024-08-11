package com.devil.ecomfashion.utils;

import com.devil.ecomfashion.modules.cart.dto.response.CartResponse;
import com.devil.ecomfashion.modules.cart.dto.response.PageableCartResponse;
import com.devil.ecomfashion.modules.cart.entity.Cart;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartUtils {

    public static CartResponse convert(Cart cart) {

        if (ObjectUtils.isEmpty(cart)) {
            return new CartResponse();
        }

        return CartResponse.builder()
                .id(cart.getId())
                .totalPrice(cart.getTotalPrice())
                .qty(cart.getQty())
                .productId(cart.getProduct().getId())
                .price(cart.getProduct().getPrice())
                .productName(cart.getProduct().getName())
                .productDescription(cart.getProduct().getDescription())
                .categoryName(cart.getProduct().getSubCategory().getCategory().getName())
                .categoryId(cart.getProduct().getSubCategory().getCategory().getId())
                .subCategoryId(cart.getProduct().getSubCategory().getId())
                .subCategoryName(cart.getProduct().getSubCategory().getName())
                .productImageUrl(cart.getProduct().getImagePath())
                .build();
    }

    public static List<CartResponse> convert(List<Cart> carts) {

        if (ObjectUtils.isEmpty(carts)) {
            return new ArrayList<>();
        }

        return carts.stream().map(CartUtils::convert)
                .collect(Collectors.toList());
    }

    public static PageableCartResponse convert(Page<Cart> cartPage) {

        if (ObjectUtils.isEmpty(cartPage)) {
            return PageableCartResponse.builder()
                    .data(new ArrayList<>()).build();
        }

        return PageableCartResponse.builder()
                .currentPage(cartPage.getTotalPages() == 0 ? 0 : cartPage.getNumber() + 1)
                .totalPages(cartPage.getTotalPages())
                .totalElements(cartPage.getNumberOfElements())
                .data(convert(cartPage.getContent())).build();
    }
}
