package com.initcloud.oauth.common.response.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* Invalid Request */
    ERROR_4001(4001, HttpStatus.BAD_REQUEST,"Invalid Request."),
    ERROR_4002(4002, HttpStatus.BAD_REQUEST,"Invalid Token."),

    /* Server Error. */
    ERROR_5001(5001, HttpStatus.INTERNAL_SERVER_ERROR,"Server Error.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}
