package ru.skillfactory.ibankApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.jsonResponse.HttpResponse;
import ru.skillfactory.ibankApi.service.UserService;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/dev")
public class DevController {

    private final UserService userService;

    @PostMapping("/fillDb")
    public HttpResponse fillDb() {
        HttpResponse httpresp = new HttpResponse();
        User user1 = new User();
        user1.setFirstName("Nick");
        user1.setLastName("Kick");
        user1.setBalance(0L);
        User user2 = new User();
        user2.setFirstName("Nick");
        user2.setLastName("Kick");
        user2.setBalance(0L);
        HttpResponse httpResponse = userService.saveUser(user1);
        HttpResponse httpResponse1 = userService.saveUser(user2);
        if (httpResponse.getStatus().equals("error")) {
            return httpResponse;
        } else if (httpResponse1.getStatus().equals("error")){
            return httpResponse1;
        } else {
            httpresp.setStatus("success");
            httpresp.setMessage("Users saved");
            return httpresp;
        }
    }
}
