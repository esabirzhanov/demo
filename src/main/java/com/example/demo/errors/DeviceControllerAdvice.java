package com.example.demo.errors;

import com.example.demo.LoadDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(DeviceControllerAdvice.class);

    @Autowired
    private Environment env;

    @ExceptionHandler(DeviceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(DeviceNotFoundException nfe) {
        String resourceKey = nfe.getKey();
        String errorCode = env.getProperty(resourceKey.concat(".errorCode"));
        String message = env.getProperty(resourceKey.concat(".message"));
        return new ErrorResponse(resourceKey, errorCode, message);
    }

    @ExceptionHandler(DeviceNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsResponse handleNotValidException(DeviceNotValidException nve) {
        List<String> errors = nve.getErrors();
        List<ErrorResponse> responses = new ArrayList<>();
        for(String resourceKey: errors) {
            String errorCode = env.getProperty(resourceKey.concat(".errorCode"));
            String message = env.getProperty(resourceKey.concat(".message"));
            responses.add(new ErrorResponse(resourceKey, errorCode, message));
        }
        return new ErrorsResponse(responses);
    }
}
