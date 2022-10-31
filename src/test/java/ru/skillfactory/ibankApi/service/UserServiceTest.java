package ru.skillfactory.ibankApi.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.skillfactory.ibankApi.entity.User;

import java.math.BigInteger;
import java.util.Optional;

@Slf4j
@ComponentScan("ru.skillfactory.ibankApi.service")
@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void saveUser() {
        User user = new User("test", "test", 1L);
        userService.saveUser(user);
        log.info("User ID: " + userService.getUserRepository().findAll().get(0).getId());
    }
    @Order(1)
    @Test
    public void getBalance() {
        log.info("User ID: " + userService.getUserRepository().findAll().get(0).getId());
        Assertions.assertEquals(1, userService.getBalance(2L).intValue());
    }
    @Order(2)
    @Test
    public void moneyOperations() {
        userService.putMoney(1L, 1L);
        User user = userService.getUserById(1L);
        Assertions.assertNotEquals(1L, user.getBalance().intValue());
        Assertions.assertEquals(2, user.getBalance());

        userService.takeMoney(1L, 1L);
        Assertions.assertEquals(1, user.getBalance());
    }
}
