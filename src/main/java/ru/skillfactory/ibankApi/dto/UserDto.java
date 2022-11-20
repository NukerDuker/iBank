package ru.skillfactory.ibankApi.dto;

import lombok.Data;
import ru.skillfactory.ibankApi.entity.Operations;

import javax.persistence.*;
import java.util.Set;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Long balance;

}
