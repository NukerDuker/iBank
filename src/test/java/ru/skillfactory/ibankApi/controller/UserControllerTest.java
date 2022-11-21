package ru.skillfactory.ibankApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.skillfactory.ibankApi.entity.User;
import ru.skillfactory.ibankApi.repository.OperationsRepository;
import ru.skillfactory.ibankApi.repository.UserRepository;
import ru.skillfactory.ibankApi.requestModels.ChangeBalanceRequest;
import ru.skillfactory.ibankApi.service.OperationsService;
import ru.skillfactory.ibankApi.service.UserService;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService mockUserService;

    @Autowired
    private OperationsService mockOperationsService;

    @Autowired
    private UserRepository mockUserRepository;

    @Autowired
    private OperationsRepository operationsRepository;

    @BeforeEach
    public void setup() {
        mockUserRepository.deleteAll();
    }

    @Test
    public void getBalanceTest() throws Exception {
        User user = User.builder()
                .firstName("Roman")
                .lastName("Zubkov")
                .balance(100L)
                .build();
        mockUserRepository.save(user);

        ResultActions response = mockMvc.perform(
                get("/getBalance/{userId}", user.getId())
        );
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.balance", is(100)));
    }

    @Test
    public void operationsTest() throws Exception {
        User user = User.builder()
                .firstName("Roman")
                .lastName("Zubkov")
                .balance(100L)
                .build();
        mockUserRepository.save(user);

        ChangeBalanceRequest request = new ChangeBalanceRequest();
        request.setUserId(user.getId().intValue());
        request.setValue(10);

        System.out.println(objectMapper.writeValueAsString(request));
        ResultActions response = mockMvc.perform(
                post("/deposit")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        user = mockUserService.getUserById(user.getId());
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.message", is("Transaction permitted")));
        Assertions.assertEquals(110, user.getBalance());

        response = mockMvc.perform(
                post("/withdrew")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
        );

        user = mockUserService.getUserById(user.getId());
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.message", is("Transaction permitted")));
        Assertions.assertEquals(100, user.getBalance());
    }
}
