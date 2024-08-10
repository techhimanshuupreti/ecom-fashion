package com.devil.ecomfashion.modules.user.service;

import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.exception.ExceptionOccur;
import com.devil.ecomfashion.modules.user.constants.Role;
import com.devil.ecomfashion.modules.user.dto.UserDTO;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public List<User> find() {
        return userRepository.findAll();
    }

    public User findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.get();
    }

    public User create(UserDTO userDTO) {
        try {
            List<User> isUserExist = userRepository.findByEmail(userDTO.getEmail());

            if (ObjectUtils.allNotNull(isUserExist) && !isUserExist.isEmpty()) {
                log.error("user already found for {}", userDTO.getEmail());
                throw new AlreadyExistException("user already found");
            }

            User user = User.builder()
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .email(userDTO.getEmail())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(Role.valueOf(userDTO.getRole()))
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .build();

            log.info("user creating for {}", userDTO.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("exception occur while creating for user details : {}", userDTO.getEmail());
            throw new ExceptionOccur("Unable to creating for user.");
        }
    }

    public User updateUser(long id, UserDTO userDTO) {
        User user = findOne(id);

        boolean isUpdated = false;

        if(!ObjectUtils.isEmpty(userDTO.getRole())) {
            user.setRole(Role.valueOf(userDTO.getRole()));
            isUpdated = true;
        }

        if(!ObjectUtils.isEmpty(userDTO.getFirstName())) {
            user.setFirstName(userDTO.getFirstName());
            isUpdated = true;
        }

        if(!ObjectUtils.isEmpty(userDTO.getLastName())) {
            user.setLastName(userDTO.getLastName());
            isUpdated = true;
        }

        if(!ObjectUtils.isEmpty(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
            isUpdated = true;
        }

        if(!ObjectUtils.isEmpty(userDTO.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            isUpdated = true;
        }

        if (isUpdated){
            user.setUpdatedAt(new Date());
        }
        return user;
    }
}
