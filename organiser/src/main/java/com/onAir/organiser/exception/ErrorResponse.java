package com.onAir.organiser.exception;

import lombok.Data;

public class ErrorResponse extends RuntimeException {
    private String message;

    private Integer code;

    public ErrorResponse(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public ErrorResponse() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
