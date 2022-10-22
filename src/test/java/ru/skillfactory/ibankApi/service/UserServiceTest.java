package ru.skillfactory.ibankApi.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skillfactory.ibankApi.entity.User;

import java.math.BigInteger;

@Slf4j
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void fillDb() {
        User user = new User("test", "test", BigInteger.ONE);
        userService.saveUser(user);
        log.info(user.getFirstName());
    }
    @Test
    public void getBalance() {
        User check = userService.getUserById(1L);
        Assertions.assertEquals(1, check.getBalance().intValue());
    }
}
