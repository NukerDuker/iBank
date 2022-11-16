package ru.skillfactory.ibankApi.jsonResponse;

import lombok.Data;
import ru.skillfactory.ibankApi.entity.Operations;

import java.util.List;

@Data
public class OperationsList implements JsonResponse{

    List<Operations> operationsList;
}
