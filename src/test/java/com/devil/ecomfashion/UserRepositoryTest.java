package com.devil.ecomfashion;

import com.devil.ecomfashion.modules.user.constants.Role;
import com.devil.ecomfashion.modules.user.entity.User;
import com.devil.ecomfashion.modules.user.respository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@DirtiesContext
@DataJpaTest
@ContextConfiguration(
        classes = {EcommerceWepApp.class}
)
@DisplayName("User Database Layer Test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    public UserRepositoryTest() {
    }

    @Test
    @Order(1)
    @DisplayName("Saving User Test")
    void saveUser() {
        User user = User.builder().firstName("TestFirst").lastName("TestLast").email("testemail@gmail.com").password(UUID.randomUUID().toString()).role(Role.valueOf("ADMIN")).createdAt(new Date()).updatedAt(new Date()).build();
        User savedUser = (User)this.userRepository.save(user);
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
        String email = "findemail@gmail.com";
        User user = User.builder().firstName("FindFirst").lastName("FindLast").email(email).password(UUID.randomUUID().toString()).role(Role.valueOf("USER")).createdAt(new Date()).updatedAt(new Date()).build();
        this.userRepository.save(user);
        Optional<User> foundUser = this.userRepository.findUserByEmail(email);
        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(email, ((User)foundUser.get()).getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("Update the User Test")
    void updateUser() {
        User user = User.builder().firstName("UpdateFirst").lastName("UpdateLast").email("updateemail@gmail.com").password(UUID.randomUUID().toString()).role(Role.valueOf("USER")).createdAt(new Date()).updatedAt(new Date()).build();
        User savedUser = (User)this.userRepository.save(user);
        savedUser.setFirstName("UpdatedFirst");
        savedUser.setLastName("UpdatedLast");
        User updatedUser = (User)this.userRepository.save(savedUser);
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals("UpdatedFirst", updatedUser.getFirstName());
        Assertions.assertEquals("UpdatedLast", updatedUser.getLastName());
    }

    @Test
    @Order(4)
    @DisplayName("Delete the User Test")
    void deleteUser() {
        User user = User.builder().firstName("DeleteFirst").lastName("DeleteLast").email("deleteemail@gmail.com").password(UUID.randomUUID().toString()).role(Role.valueOf("USER")).createdAt(new Date()).updatedAt(new Date()).build();
        User savedUser = (User)this.userRepository.save(user);
        this.userRepository.delete(savedUser);
        Optional<User> deletedUser = this.userRepository.findById(savedUser.getId());
        Assertions.assertFalse(deletedUser.isPresent());
    }

    @Test
    @Order(5)
    @DisplayName("Fetch ALL User Test")
    void findAllUsers() {
        User user1 = User.builder().firstName("First1").lastName("Last1").email("email1@gmail.com").password(UUID.randomUUID().toString()).role(Role.valueOf("USER")).createdAt(new Date()).updatedAt(new Date()).build();
        User user2 = User.builder().firstName("First2").lastName("Last2").email("email2@gmail.com").password(UUID.randomUUID().toString()).role(Role.valueOf("USER")).createdAt(new Date()).updatedAt(new Date()).build();
        this.userRepository.save(user1);
        this.userRepository.save(user2);
        List<User> users = this.userRepository.findAll();
        Assertions.assertFalse(users.isEmpty());
        Assertions.assertTrue(users.size() >= 2);
    }
}
