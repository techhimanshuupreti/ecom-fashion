package com.devil.ecomfashion.repository;

import com.devil.ecomfashion.modules.user.constants.Role;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.respository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/*
 * Todo: When run database layer test. You must do these things first.
 *  1. spring.profiles.active=test
 *  2. h2 and Junit dependency in pom
 * */
@DataJpaTest
@DisplayName("User Database Layer Test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeEach
    void init() {
        user = User.builder().firstName("TestFirst").lastName("TestLast").email("testemail@gmail.com").password(UUID.randomUUID().toString()).role(Role.valueOf("ADMIN")).createdAt(new Date()).updatedAt(new Date()).build();
    }

    @Test
    @Order(1)
    @DisplayName("Saving User Test")
    void saveUser() {
        User savedUser = userRepository.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertEquals(savedUser.getFirstName(), "TestFirst");
        Assertions.assertEquals(savedUser.getLastName(), "TestLast");
        Assertions.assertEquals(savedUser.getEmail(), "testemail@gmail.com");
    }

    @Test
    @Order(2)
    @DisplayName("Fetch User By Email Test")
    void findUserByEmail() {
        String email = "testemail@gmail.com";
        userRepository.save(user);
        Optional<User> foundUser = userRepository.findUserByEmail(email);
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(email, ((User) foundUser.get()).getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("Update the User Test")
    void updateUser() {
        User savedUser = userRepository.save(user);
        savedUser.setFirstName("UpdatedFirst");
        savedUser.setLastName("UpdatedLast");
        User updatedUser = userRepository.save(savedUser);
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals("UpdatedFirst", updatedUser.getFirstName());
        Assertions.assertEquals("UpdatedLast", updatedUser.getLastName());
    }

    @Test
    @Order(4)
    @DisplayName("Delete the User Test")
    void deleteUser() {
        User savedUser = userRepository.save(user);
        userRepository.delete(savedUser);
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        Assertions.assertFalse(deletedUser.isPresent());
    }

    @Test
    @Order(5)
    @DisplayName("Fetch ALL User Test")
    void findAllUsers() {
        User user2 = User.builder().firstName("First2").lastName("Last2").email("email2@gmail.com").password(UUID.randomUUID().toString()).role(Role.valueOf("USER")).createdAt(new Date()).updatedAt(new Date()).build();
        userRepository.save(user);
        userRepository.save(user2);
        List<User> users = userRepository.findAll();
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertTrue(users.size() >= 2);
    }

    @Test
    @Order(6)
    @DisplayName("throwing Exception while Fetch Specific User Test")
    void findSpecificUsers() {

        userRepository.save(user);
        Assertions.assertThrows(RuntimeException.class, () -> userRepository.findUserByEmail("email21@gmail.com").orElseThrow());
    }
}
