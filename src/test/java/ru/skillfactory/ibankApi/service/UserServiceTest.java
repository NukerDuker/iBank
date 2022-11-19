package ru.skillfactory.ibankApi.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.exceptions.ControllerException;

@Slf4j
@DataJpaTest
@ComponentScan("ru.skillfactory.ibankApi.service")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private long id;

    @BeforeEach
    void saveUser() {
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Testing");
        user.setBalance(1L);
        userService.saveUser(user);
        id = user.getId();
        log.info("User ID: " + userService.getUserRepository().findAll().get(0).getId());
    }
    @Test
    public void getBalance() {
        log.info("User ID: " + userService.getUserRepository().findAll().get(0).getId());
        Assertions.assertEquals(1, userService.getUserBalance(id).intValue());
    }
    @Test
    public void moneyOperations() throws ControllerException {
        userService.putMoney(1L, 1L);
        User user = userService.getUserById(id);
        Assertions.assertNotEquals(1L, user.getBalance().intValue());
        Assertions.assertEquals(2, user.getBalance());

        userService.takeMoney(id, 1L);
        Assertions.assertEquals(1, user.getBalance());
    }
}
