package com.example.demo.errors;

import java.util.List;

public class ErrorsResponse {
    List<ErrorResponse> errorResponses;

    public ErrorsResponse() {}

    public ErrorsResponse(List<ErrorResponse> errorResponses) {
        this.errorResponses = errorResponses;
    }

    public List<ErrorResponse> getErrorResponses() {
        return errorResponses;
    }
}
