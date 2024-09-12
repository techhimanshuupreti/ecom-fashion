package com.devil.ecomfashion.modules.cart.service;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.exception.RequestValidationException;
import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.cart.dto.request.CartRequestDTO;
import com.devil.ecomfashion.modules.cart.dto.response.CartResponse;
import com.devil.ecomfashion.modules.cart.dto.response.PageableCartResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    public PageableCartResponse findAll(User user, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo >= 1 ? pageNo - 1 : 0, pageSize, Sort.by("createdAt")
                .descending().and(Sort.by("id").descending()));

        Page<Cart> categoryPage = cartRepository.findByIsDeletedIsFalseAndUserId(user.getId(), pageable);

        return CartUtils.convert(categoryPage);
    }

    @Transactional
    public CartResponse create(CartRequestDTO cartRequestDTO, User user) {
        Cart cart = createCart(cartRequestDTO, user);
        return CartUtils.convert(cart);
    }

    @Transactional
    public boolean delete(User user, Long cartId) {
        Optional<Cart> cart = cartRepository.findByIsDeletedIsFalseAndIdAndUserId(cartId, user.getId());
        if (cart.isEmpty()) {
            throw new ResourceNotFoundException(Message.NO_CART_FOUND);
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
            throw new ResourceNotFoundException(Message.NO_CART_FOUND);
        }


        if (cart.get().getProduct().getStock() >= cart.get().getQty()) {
            throw new RequestValidationException(Message.INVALID_PRODUCT_QTY);
        }
        cart.get().setUpdatedAt(new Date());
        cart.get().setQty(qty);
        Cart updatedCart = cartRepository.save(cart.get());
        return CartUtils.convert(updatedCart);
    }

    public Cart getByProductId(User user, Long productId) {
        return cartRepository.getCartsByProductId(productId, user.getId());
    }

    @Transactional
    public Cart createCart(CartRequestDTO cartRequestDTO, User user) {

        Product product = productService.getById(cartRequestDTO.getProductId());
        if (ObjectUtils.isEmpty(product)) {
            throw new ResourceNotFoundException(Message.PRODUCT_NOT_FOUND);
        }

        if (product.getStock() >= cartRequestDTO.getQty()) {
            throw new RequestValidationException(Message.INVALID_PRODUCT_QTY);
        }

        Cart newCart = new Cart();
        newCart.setQty(cartRequestDTO.getQty());
        newCart.setUserId(user.getId());
        newCart.setProduct(product);
        newCart.setTotalPrice(product.getPrice() * cartRequestDTO.getQty());
        newCart.setCreatedAt(new Date());
        newCart.setUpdatedAt(new Date());
        return cartRepository.save(newCart);
    }
}
