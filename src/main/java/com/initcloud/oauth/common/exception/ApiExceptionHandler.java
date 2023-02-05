package com.initcloud.oauth.common.exception;


import com.initcloud.oauth.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> throwApiException(ApiException e){
        return ApiResponse.throwException(e);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> throwException(Exception e){
        return ApiResponse.throwException(e);
    }
}
