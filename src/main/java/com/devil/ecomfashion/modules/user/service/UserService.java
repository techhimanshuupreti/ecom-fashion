package com.devil.ecomfashion.modules.user.service;

import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> find() {
        return userRepository.findAll();
    }

    public User findOne(long id) {
        return userRepository.findById(id).orElseGet(User::new);
    }
}
