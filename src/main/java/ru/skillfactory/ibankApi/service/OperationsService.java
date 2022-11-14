package ru.skillfactory.ibankApi.service;

import lombok.Data;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillfactory.ibankApi.entity.Operations;
import ru.skillfactory.ibankApi.exceptions.Response;
import ru.skillfactory.ibankApi.repository.OperationsRepository;
import ru.skillfactory.ibankApi.requestModels.GetOperationsRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Data
public class OperationsService {

    @Autowired
    private OperationsRepository operationsRepository;

    DateFormat formatter = new SimpleDateFormat("d-MM-yyyy");

    public Response getOperationList(GetOperationsRequest request) throws ParseException {
        List<Operations> operations;
        try {
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
            Map<String, Object> message = new LinkedHashMap<>();
            Response response = new Response(message);
            message.put("message", "Transaction permitted");
            message.put("operations", operations);
            return response;
        } catch (ObjectNotFoundException e) {
            Map<String, Object> message = new HashMap<>();
            Response response = new Response(message);
            message.put("error", "No operations of given period found");
            return response;
        }


    }

}
