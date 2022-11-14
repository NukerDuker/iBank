package ru.skillfactory.ibankApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ControllerException.class)
    public ResponseEntity<Response> handleException(ControllerException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        Response response = new Response(Map.of("message", message));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
