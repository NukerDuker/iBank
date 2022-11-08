package ru.skillfactory.ibankApi.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Optional;


@Data
public class GetOperationsRequest {

    private long userId;

    @Nullable
    private Optional<String> startDate;

    @Nullable
    private String endDate;

}
