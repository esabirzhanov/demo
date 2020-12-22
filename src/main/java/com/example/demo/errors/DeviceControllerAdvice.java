package com.example.demo.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class DeviceControllerAdvice {

    @Autowired
    private Environment env;

    @ExceptionHandler(DeviceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(DeviceNotFoundException nfe) {
        String resourceKey = env.getProperty(nfe.getMessage());
        String message = env.getProperty(resourceKey);
        return new ErrorResponse(resourceKey, nfe.getMessage(), message);

    }

    @ExceptionHandler(DeviceNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleNotValidException(DeviceNotValidException nve) {
        List<String> errors = nve.getErrors();
        List<ErrorResponse> responses = new ArrayList<>();
        for(String error: errors) {
            String resourceKey = env.getProperty(error);
            responses.add(new ErrorResponse(resourceKey, error, env.getProperty(resourceKey)));
        }
        return new ErrorsResponse(responses);
    }
}
