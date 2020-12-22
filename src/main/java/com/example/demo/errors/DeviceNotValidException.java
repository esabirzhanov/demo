package com.example.demo.errors;

import java.util.List;

public class DeviceNotValidException extends RuntimeException {
    private List<String> errors;
    public DeviceNotValidException() {}
    public DeviceNotValidException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
