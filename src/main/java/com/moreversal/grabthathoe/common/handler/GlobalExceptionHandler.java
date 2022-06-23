package com.moreversal.grabthathoe.common.handler;

import com.moreversal.grabthathoe.common.exception.AuthorizationException;
import com.moreversal.grabthathoe.common.exception.DuplicateRecordException;
import com.moreversal.grabthathoe.common.exception.ForbiddenException;
import com.moreversal.grabthathoe.common.exception.RecordNotFoundException;
import com.moreversal.grabthathoe.common.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse handleNullPointerException(NullPointerException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "NullPointerException");
    }

    @ExceptionHandler(AuthorizationException.class)
    public ErrorResponse handleAuthorizationException(AuthorizationException e) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage(), "AuthorizationException");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorResponse handleExpiredJwtException(ExpiredJwtException e) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "JWT 토큰이 만료되었습니다", "ExpiredJwtException");
    }

    @ExceptionHandler(SignatureException.class)
    public ErrorResponse handleSignatureException(SignatureException e) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "JWT 토큰이 위조되었습니다", "SignatureException");
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ErrorResponse handleMalformedJwtException(MalformedJwtException e) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "JWT 토큰이 위조되었습니다", "MalformedJwtException");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorResponse(HttpStatus.NOT_ACCEPTABLE, "요청 인자가 잘못되었습니다", "IllegalArgumentException");
    }

    @ExceptionHandler(IOException.class)
    public ErrorResponse handleIOException(IOException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 처리 과정에서 오류가 발생했습니다", "IOException");
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ErrorResponse handleDuplicateRecordException(DuplicateRecordException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), "DuplicateRecordException");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "요청 메서드가 잘못되었습니다", "HttpRequestMethodNotSupportedException");
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ErrorResponse handleRecordNotFoundException(RecordNotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, "해당 행을 찾지 못했습니다", "RecordNotFoundException");
    }

    @ExceptionHandler(ForbiddenException.class)
    public ErrorResponse handleForbiddenException(ForbiddenException e) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, e.getMessage(), "ForbiddenException");
    }

}