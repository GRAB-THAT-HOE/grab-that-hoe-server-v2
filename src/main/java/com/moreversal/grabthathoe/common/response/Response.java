package com.moreversal.grabthathoe.common.response;

import org.springframework.http.HttpStatus;

public class Response {
    private final int status;
    private final String message;

    public Response(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
