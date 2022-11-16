package ru.skillfactory.ibankApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillfactory.ibankApi.entity.Operations;
import ru.skillfactory.ibankApi.jsonResponse.HttpResponse;
import ru.skillfactory.ibankApi.jsonResponse.JsonResponse;
import ru.skillfactory.ibankApi.jsonResponse.OperationsList;
import ru.skillfactory.ibankApi.repository.OperationsRepository;
import ru.skillfactory.ibankApi.requestModels.GetOperationsRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Data
public class OperationsService {

    @Autowired
    private OperationsRepository operationsRepository;

    DateFormat formatter = new SimpleDateFormat("d-MM-yyyy");

    public JsonResponse getOperationList(GetOperationsRequest request) throws ParseException {
        List<Operations> operations;
        ObjectMapper mapper = new ObjectMapper();
            boolean check = request.getEndDate() == null;
            System.out.println("request.getDate: " + request.getStartDate() + "\n" + check);
            if (request.getEndDate() == null) {
                if (request.getStartDate() == null) {
                    operations = operationsRepository.findAllByUserId(request.getUserId());
                } else {
                    System.out.println("INSIDE 2  BLOCK");
                    operations = operationsRepository.findAllByUserIdAndDateAfter(request.getUserId(), request.getStartDate());
                }
            } else {
                System.out.println("INSIDE 3 Block");
                operations = operationsRepository.findAllByUserIdAndDateBetween(request.getUserId(), request.getStartDate(), request.getEndDate());
            }
        HttpResponse httpResponse = new HttpResponse();
        OperationsList operationsList = new OperationsList();
        if (operations.isEmpty()) {
            httpResponse.setStatus("success");
            httpResponse.setMessage("Operations not found");
            return httpResponse;
        }
        httpResponse.setStatus("success");
        httpResponse.setMessage(operations);
        operationsList.setOperationsList(operations);
//            return httpResponse;
        return operationsList;

    }

}
