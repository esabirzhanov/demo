package com.example.demo.errors;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() {}

    public DeviceNotFoundException(String message) {
        super(message);
    }
}
