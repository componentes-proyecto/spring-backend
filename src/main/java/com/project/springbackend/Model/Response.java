package com.project.springbackend.Model;

import java.util.List;

public class Response extends Model{
    private int status_code;
    private String message;
    private List<Model> data;

    public Response(int status_code, String message, List<Model> data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }
}
