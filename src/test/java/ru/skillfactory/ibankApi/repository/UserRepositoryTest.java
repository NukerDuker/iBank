package ru.skillfactory.ibankApi.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.repository.UserRepository;

import java.math.BigInteger;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user = new User("Harry", "Potter",98000000L);

    @Test
    void getUserById() {
        userRepository.save(user);
        User harry = userRepository.findById(1L).get();
        Assertions.assertNotNull(harry);
        Assertions.assertNotNull(harry.getBalance());
        Assertions.assertEquals(98000000, harry.getBalance().intValue());
    }

}