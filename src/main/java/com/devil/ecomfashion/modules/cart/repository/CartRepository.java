package com.devil.ecomfashion.modules.cart.repository;

import com.devil.ecomfashion.modules.cart.entity.Cart;
import com.devil.ecomfashion.modules.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart,Long> {

    List<Cart> findByIsDeletedIsFalseAndUserIn(Collection<User> users);
    Page<Cart> findByIsDeletedIsFalseAndUserIn(Collection<User> users, Pageable pageable);

    Optional<Cart> findByIsDeletedIsFalseAndIdAndUser(Long cartId, User user);
}
