package ru.skillfactory.ibankApi.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.ibankApi.dto.ChangeBalanceRequest;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.jsonResponse.BalanceResponseBuilder;
import ru.skillfactory.ibankApi.service.UserService;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    @NonNull
    private BalanceResponseBuilder responseBuilder;

    @GetMapping("/getBalance/{userId}")
    public User getBalance(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> putMoney(@RequestBody ChangeBalanceRequest income) {
        userService.putMoney((long) income.getUserId(),(long) income.getValue());
        return ResponseEntity.ok("Transaction permitted");
    }
    @PostMapping("/withdrow")
    public ResponseEntity<String> takeMoney(@RequestBody ChangeBalanceRequest spend) {
        return userService.takeMoney((long) spend.getUserId(),(long) spend.getValue());
    }
}
