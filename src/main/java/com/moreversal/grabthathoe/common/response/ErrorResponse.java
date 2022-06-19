package com.moreversal.grabthathoe.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse extends Response {
    private final String error;

    public ErrorResponse(HttpStatus status, String message, String error) {
        super(status, message);
        this.error = error;
    }
}
