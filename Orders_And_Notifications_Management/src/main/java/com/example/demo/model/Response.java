package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;

public class Response {

    private boolean status;
    private String message;
    public Object data ;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

