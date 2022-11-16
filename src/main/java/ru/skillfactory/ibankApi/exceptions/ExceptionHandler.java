package ru.skillfactory.ibankApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.skillfactory.ibankApi.jsonResponse.HttpResponse;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ControllerException.class)
    public ResponseEntity<HttpResponse> handleException(ControllerException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatus("error");
        httpResponse.setMessage(message);
        return new ResponseEntity<>(httpResponse, HttpStatus.OK);
    }
}
