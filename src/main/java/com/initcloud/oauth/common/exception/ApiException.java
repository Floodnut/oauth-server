package com.initcloud.oauth.common.exception;

import com.initcloud.oauth.common.response.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class ApiException extends RuntimeException{
    private Throwable ex;
    private final ErrorCode errorCode;
}
