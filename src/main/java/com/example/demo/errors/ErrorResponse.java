package com.example.demo.errors;

public class ErrorResponse {
    private String resourceKey;
    private String errorCode;
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(String resourceKey, String errorCode, String message) {
        this.resourceKey = resourceKey;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
