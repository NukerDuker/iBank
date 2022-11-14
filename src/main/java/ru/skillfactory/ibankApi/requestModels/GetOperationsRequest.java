package ru.skillfactory.ibankApi.requestModels;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.Optional;


@Data
public class GetOperationsRequest {

    private long userId;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

}
