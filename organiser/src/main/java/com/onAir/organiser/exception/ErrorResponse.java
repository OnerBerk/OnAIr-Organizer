package com.onAir.organiser.exception;

import lombok.Data;
import lombok.Getter;

@Data
public class ErrorResponse extends RuntimeException {
    public ErrorResponse(String message, Integer code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public ErrorResponse() {
        super();
    }

    @Getter
    private String message;

    @Getter
    private Integer code;
    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
