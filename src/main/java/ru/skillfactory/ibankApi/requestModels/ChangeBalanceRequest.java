package ru.skillfactory.ibankApi.requestModels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeBalanceRequest {

    private int userId;
    private int userToId;
    private int value;
}
