package ru.skillfactory.ibankApi.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Long getBalance(Long id) {
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

    public Long takeMoney(Long id, Long spend) {
        Optional<User> user = userRepository.findById(id);
        Long newBalance = user.get().getBalance() - spend;
        user.get().setBalance(newBalance);
        userRepository.save(user.get());
        return newBalance;
    }
}
