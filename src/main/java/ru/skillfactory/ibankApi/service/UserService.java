package ru.skillfactory.ibankApi.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.repository.UserRepository;

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

    public Long getUserBalance(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get().getBalance();
    }

    public Long putMoney(Long id, Long income) {
        Optional<User> user = userRepository.findById(id);
        Long newBalance = user.get().getBalance() + income;
        user.get().setBalance(newBalance);
        userRepository.save(user.get());
        return newBalance;
    }

    public ResponseEntity<String> takeMoney(Long id, Long spend) {
        Optional<User> user = userRepository.findById(id);
        Long newBalance = user.get().getBalance() - spend;
        if (newBalance < 0) {
            return ResponseEntity.of(Optional.of("Not enough money"));
        }
        user.get().setBalance(newBalance);
        userRepository.save(user.get());
        return ResponseEntity.ok("Transaction permitted");
    }
}
