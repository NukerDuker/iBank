package ru.skillfactory.ibankApi.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.ibankApi.dto.ChangeBalanceRequest;
import ru.skillfactory.ibankApi.dto.GetOperationsRequest;
import ru.skillfactory.ibankApi.entity.Operations;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.exceptions.ControllerException;
import ru.skillfactory.ibankApi.exceptions.Response;
import ru.skillfactory.ibankApi.jsonResponse.BalanceResponseBuilder;
import ru.skillfactory.ibankApi.service.OperationsService;
import ru.skillfactory.ibankApi.service.UserService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final OperationsService operationsService;

    @GetMapping("/getBalance/{userId}")
    public User getBalance(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/deposit")
    public Response putMoney(@RequestBody ChangeBalanceRequest income) throws ControllerException {
        return userService.putMoney((long) income.getUserId(),(long) income.getValue());
    }
    @PostMapping("/withdrew")
    public Response takeMoney(@RequestBody ChangeBalanceRequest spend) throws ControllerException {
        return userService.takeMoney((long) spend.getUserId(),(long) spend.getValue());
    }

    @PostMapping("/getOperationList")
    public List<Operations> getOperations(@RequestBody GetOperationsRequest request) throws ParseException {
        return operationsService.getOperationList(request.getUserId());
    }
}
