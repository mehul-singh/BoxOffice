package com.boxoffice.entity;

public class ErrorResponse {

    String message;
    public ErrorResponse(){}
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }
}
