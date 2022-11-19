package ru.skillfactory.ibankApi.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillfactory.ibankApi.entity.Operations;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.exceptions.ControllerException;
import ru.skillfactory.ibankApi.jsonResponse.HttpResponse;
import ru.skillfactory.ibankApi.repository.OperationsRepository;
import ru.skillfactory.ibankApi.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationsRepository operationsRepository;

    public HttpResponse saveUser(User user) {
        HttpResponse response = new HttpResponse();
        User usr = userRepository.save(user);
        if (userRepository.findById(usr.getId()).isPresent()) {
            response.setStatus("success");
            response.setMessage(usr.toString());
        } else {
            response.setStatus("error");
            response.setMessage("something went wrong");
        }
        return response;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public Long getUserBalance(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get().getBalance();
    }
    public HttpResponse putMoney(Long id, Long income) throws ControllerException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Long newBalance = user.get().getBalance() + income;
            user.get().setBalance(newBalance);
            userRepository.save(user.get());
            Operations operation = new Operations();
            operation.setUserId(user.get().getId());
            operation.setOperationType(1);
            operation.setAmount(income);
            operationsRepository.save(operation);

            HttpResponse response = new HttpResponse();
            response.setStatus("success");
            response.setMessage("Transaction permitted");
            return response;
        } else {
            throw new ControllerException("User not found");
        }
    }

    public HttpResponse takeMoney(Long id, Long spend) throws ControllerException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            long newBalance = user.get().getBalance() - spend;
            if (newBalance < 0) {
                HttpResponse response = new HttpResponse();
                response.setStatus("error");
                response.setMessage("Not enough money");
                return response;
            }
            user.get().setBalance(newBalance);
            userRepository.save(user.get());
            Operations operation = new Operations();
            operation.setUserId(user.get().getId());
            operation.setOperationType(0);
            operation.setAmount(spend);
            operationsRepository.save(operation);
            HttpResponse response = new HttpResponse();
            response.setStatus("success");
            response.setMessage("Transaction permitted");
            return response;
        } else {
            throw new ControllerException("User not found");
        }
    }

    public HttpResponse sendMoney(long fromUserId, long toUserId, long operationAmount) throws ControllerException {
        boolean checkBalance = false;
        Optional<User> userFrom = userRepository.findById(fromUserId);
        if (userFrom.isPresent()) {
            checkBalance = userFrom.get().getBalance() > operationAmount;
        } else {
            HttpResponse response = new HttpResponse();
            response.setStatus("error");
            response.setMessage("User not found");
            return response;
        }
        if (checkBalance) {
            this.takeMoney(fromUserId, operationAmount);
            this.putMoney(toUserId, operationAmount);
        } else {
            HttpResponse httpResponse = new HttpResponse();
            httpResponse.setStatus("error");
            httpResponse.setMessage("Not enough money");
            return httpResponse;
        }
        HttpResponse response = new HttpResponse();
        response.setStatus("success");
        response.setMessage("Transaction permitted");
        return response;
    }
}
