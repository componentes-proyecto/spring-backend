package com.project.springbackend.Controller.ApiResponse;

public class ApiResponse {
    private String message;
    private boolean status;
    private Object data;

    public ApiResponse(String message, boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
