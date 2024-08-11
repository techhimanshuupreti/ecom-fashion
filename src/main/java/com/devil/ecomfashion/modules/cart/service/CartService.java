package com.devil.ecomfashion.modules.cart.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.cart.dto.request.CartRequestDTO;
import com.devil.ecomfashion.modules.cart.entity.Cart;
import com.devil.ecomfashion.modules.cart.repository.CartRepository;
import com.devil.ecomfashion.modules.product.entiry.Product;
import com.devil.ecomfashion.modules.product.service.ProductService;
import com.devil.ecomfashion.modules.user.entity.User;
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

    public List<Cart> findAll(User user) {
        List<Cart> carts = cartRepository.findByIsDeletedIsFalseAndUserIn(Collections.singletonList(user));
        if (ObjectUtils.isEmpty(carts)) {
            return Collections.emptyList();
        }
        return carts;
    }

    @Transactional
    public Cart create(CartRequestDTO cartRequestDTO, User user) {

        Product product = productService.getById(cartRequestDTO.getProductId());
        if (ObjectUtils.isEmpty(product)) {
            throw new ResourceNotFoundException("Product not found");
        }

        Cart newCart = new Cart();
        newCart.setQty(cartRequestDTO.getQty());
        newCart.setUser(user);
        newCart.setProduct(product);
        newCart.setCreatedAt(new Date());
        newCart.setUpdatedAt(new Date());
        return cartRepository.save(newCart);
    }

    public boolean remove(Long cartId, User user) {
        Optional<Cart> cart = cartRepository.findByIsDeletedIsFalseAndIdAndUser(cartId, user);
        if (cart.isEmpty()) {
            throw new ResourceNotFoundException("Entry not found");
        }

        cart.get().setUpdatedAt(new Date());
        cart.get().setDeleted(true);
        cartRepository.save(cart.get());
        return true;
    }

    public Cart updateQty(Long cartId, User user, int qty) {
        Optional<Cart> cart = cartRepository.findByIsDeletedIsFalseAndIdAndUser(cartId, user);
        if (cart.isEmpty()) {
            throw new ResourceNotFoundException("Entry not found");
        }

        cart.get().setUpdatedAt(new Date());
        cart.get().setQty(qty);
        return cartRepository.save(cart.get());
    }
}
