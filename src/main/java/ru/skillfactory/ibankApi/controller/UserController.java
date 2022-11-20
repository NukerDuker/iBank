package ru.skillfactory.ibankApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.ibankApi.dto.User2UserDtoConverter;
import ru.skillfactory.ibankApi.dto.UserDto;
import ru.skillfactory.ibankApi.jsonResponse.JsonResponse;
import ru.skillfactory.ibankApi.requestModels.ChangeBalanceRequest;
import ru.skillfactory.ibankApi.requestModels.GetOperationsRequest;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.exceptions.ControllerException;
import ru.skillfactory.ibankApi.jsonResponse.HttpResponse;
import ru.skillfactory.ibankApi.service.OperationsService;
import ru.skillfactory.ibankApi.service.UserService;

import java.text.ParseException;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    private final OperationsService operationsService;

    @GetMapping("/getBalance/{userId}")
    public UserDto getBalance(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        User2UserDtoConverter converter = new User2UserDtoConverter();
        return converter.convertUserToUserDto(user);
    }

    @PostMapping("/deposit")
    public HttpResponse putMoney(@RequestBody ChangeBalanceRequest income) throws ControllerException {
        return userService.putMoney((long) income.getUserId(),(long) income.getValue());
    }
    @PostMapping("/withdrew")
    public HttpResponse takeMoney(@RequestBody ChangeBalanceRequest spend) throws ControllerException {
        return userService.takeMoney((long) spend.getUserId(),(long) spend.getValue());
    }

    @PostMapping("/getOperationList")
    public JsonResponse getOperations(@RequestBody GetOperationsRequest request) throws ParseException {
        log.info(request.toString());
        return operationsService.getOperationList(request);
    }

    @PostMapping("/transferMoney")
    public HttpResponse transferMoney(@RequestBody ChangeBalanceRequest request) throws ControllerException {
        return userService.sendMoney(request.getUserId(), request.getUserToId(), request.getValue());
    }
}
