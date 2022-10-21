package ru.skillfactory.ibankApi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.repository.UserRepository;

import java.math.BigInteger;

@DataJpaTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    private User user = new User("Harry", "Potter", BigInteger.valueOf(98000000));

//    @BeforeEach
//    void fillDb() {
//        userService.saveUser(user);
//    }

    @Test
    void getUserById() {
        userRepository.save(user);
        User harry = userRepository.findById(1L).get();
        Assertions.assertNotNull(harry);
        Assertions.assertNotNull(harry.getBalance());
    }

    @Test
    void getBalance() {
//        Assertions.assertNotNull(userService.getBalance(user));
    }
}