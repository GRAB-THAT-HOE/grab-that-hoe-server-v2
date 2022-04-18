package com.moreversal.grabthathoe.common.response;

import org.springframework.http.HttpStatus;

public class DataResponse<T> extends Response {
    private final T data;

    public DataResponse(HttpStatus status, String message, T data) {
        super(status, message);
        this.data = data;
    }
}
