package com.devil.ecomfashion.modules.cart.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.cart.dto.request.CartRequestDTO;
import com.devil.ecomfashion.modules.cart.dto.response.CartResponse;
import com.devil.ecomfashion.modules.cart.entity.Cart;
import com.devil.ecomfashion.modules.cart.repository.CartRepository;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.service.ProductService;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.utils.CartUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    @Transactional
    public List<CartResponse> findAll(User user) {
        List<Cart> carts = cartRepository.findByIsDeletedIsFalseAndUserId(user.getId());
        if (ObjectUtils.isEmpty(carts)) {
            return Collections.emptyList();
        }
        return CartUtils.convert(carts);
    }

    @Transactional
    public CartResponse create(CartRequestDTO cartRequestDTO, User user) {

        Product product = productService.getById(cartRequestDTO.getProductId());
        if (ObjectUtils.isEmpty(product)) {
            throw new ResourceNotFoundException("Product not found");
        }

        Cart newCart = new Cart();
        newCart.setQty(cartRequestDTO.getQty());
        newCart.setUserId(user.getId());
        newCart.setProduct(product);
        newCart.setTotalPrice(product.getPrice() * cartRequestDTO.getQty());
        newCart.setCreatedAt(new Date());
        newCart.setUpdatedAt(new Date());
        newCart = cartRepository.save(newCart);
        return CartUtils.convert(newCart);
    }

    @Transactional
    public boolean delete(User user, Long cartId) {
        Optional<Cart> cart = cartRepository.findByIsDeletedIsFalseAndIdAndUserId(cartId, user.getId());
        if (cart.isEmpty()) {
            throw new ResourceNotFoundException("Entry not found");
        }

        cart.get().setUpdatedAt(new Date());
        cart.get().setDeleted(true);
        cartRepository.save(cart.get());
        return true;
    }

    @Transactional
    public CartResponse updateQty(User user, Long cartId, int qty) {
        Optional<Cart> cart = cartRepository.findByIsDeletedIsFalseAndIdAndUserId(cartId, user.getId());
        if (cart.isEmpty()) {
            throw new ResourceNotFoundException("Entry not found");
        }

        cart.get().setUpdatedAt(new Date());
        cart.get().setQty(qty);
        Cart updatedCart =  cartRepository.save(cart.get());
        return CartUtils.convert(updatedCart);
    }
}
