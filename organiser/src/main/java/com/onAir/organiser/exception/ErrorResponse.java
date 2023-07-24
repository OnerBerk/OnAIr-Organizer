package com.onAir.organiser.exception;

import lombok.Data;

public class ErrorResponse extends RuntimeException {
    private String message;

    public ErrorResponse(String message) {
        super(message);
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
