package ru.skillfactory.ibankApi.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.repository.UserRepository;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public BigInteger getBalance(User user) {
        return user.getBalance();
    }
}
