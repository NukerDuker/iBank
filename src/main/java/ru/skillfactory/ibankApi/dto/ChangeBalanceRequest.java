package ru.skillfactory.ibankApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeBalanceRequest {

    private int userId;
    private int value;
}
