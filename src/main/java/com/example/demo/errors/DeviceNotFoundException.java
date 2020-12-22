package com.example.demo.errors;

public class DeviceNotFoundException extends RuntimeException {
    private String key;
    public DeviceNotFoundException() {}

    public DeviceNotFoundException(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
