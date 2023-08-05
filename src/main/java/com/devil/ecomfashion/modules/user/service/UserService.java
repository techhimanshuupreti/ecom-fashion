package com.devil.ecomfashion.modules.user.service;

import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> find(){
        List<User> users = userRepository.findAll();
        return users;
    }
}
