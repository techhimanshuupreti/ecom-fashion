package com.devil.ecomfashion.modules.cart.repository;

import com.devil.ecomfashion.modules.cart.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    Optional<Cart> findByIsDeletedIsFalseAndIdAndUserId(Long cartId, Long userId);

    List<Cart> findByIsDeletedIsFalseAndUserId(Long userId);

    Page<Cart> findByIsDeletedIsFalseAndUserId(Long userId, Pageable pageable);

    @Query(value = "SELECT * FROM carts WHERE user_id=:userId AND product_id = :productId AND is_deleted = false", nativeQuery = true)
    Cart getCartsByProductId(@Param("productId") Long productId, @Param("userId") Long userId);

}
