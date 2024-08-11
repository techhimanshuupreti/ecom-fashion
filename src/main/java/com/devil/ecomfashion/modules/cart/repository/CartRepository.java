package com.devil.ecomfashion.modules.cart.repository;

import com.devil.ecomfashion.modules.cart.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart,Long> {

    Optional<Cart> findByIsDeletedIsFalseAndIdAndUserId(Long cartId, Long userId);

    List<Cart> findByIsDeletedIsFalseAndUserId(Long userId);
    Page<Cart> findByIsDeletedIsFalseAndUserId(Long userId,Pageable pageable);

}
