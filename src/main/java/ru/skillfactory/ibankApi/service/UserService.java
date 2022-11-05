package ru.skillfactory.ibankApi.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.exceptions.ControllerException;
import ru.skillfactory.ibankApi.exceptions.Response;
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

    public Response putMoney(Long id, Long income) throws ControllerException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Long newBalance = user.get().getBalance() + income;
            user.get().setBalance(newBalance);
            userRepository.save(user.get());
            return new Response("Transaction permitted");
        } else {
            throw new ControllerException("User not found");
        }
    }

    public Response takeMoney(Long id, Long spend) throws ControllerException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            long newBalance = user.get().getBalance() - spend;
            if (newBalance < 0) {
                return new Response("Not enough money");
            }
            user.get().setBalance(newBalance);
            userRepository.save(user.get());
        } else {
            throw new ControllerException("User not found");
        }
        return new Response("Transaction permitted");
    }
}
