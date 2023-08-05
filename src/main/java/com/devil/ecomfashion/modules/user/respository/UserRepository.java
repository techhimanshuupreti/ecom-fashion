package com.devil.ecomfashion.modules.user.respository;

import com.devil.ecomfashion.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);

    List<User> findByEmail(String email);
}
