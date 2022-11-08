package ru.skillfactory.ibankApi.service;

import lombok.Data;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.skillfactory.ibankApi.dto.GetOperationsRequest;
import ru.skillfactory.ibankApi.entity.Operations;
import ru.skillfactory.ibankApi.repository.OperationsRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Data
public class OperationsService {

    @Autowired
    private OperationsRepository operationsRepository;

    DateFormat formatter = new SimpleDateFormat("d-MM-yyyy");

    public List<Operations> getOperationList(Long userId) throws ParseException {
        List<Operations> operations = operationsRepository.findAllByUserId(userId);
        if (operations.size() > 0) {
            return operations;
        } else {
            throw new ObjectNotFoundException("Error", "No operations found");
        }
    }

}
