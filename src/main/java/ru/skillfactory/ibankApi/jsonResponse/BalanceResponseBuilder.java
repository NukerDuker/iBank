package ru.skillfactory.ibankApi.jsonResponse;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class BalanceResponseBuilder {

    private long balance;
}
