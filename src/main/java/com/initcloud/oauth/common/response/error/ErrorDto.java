package com.initcloud.oauth.common.response.error;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto {
    private final int code;
    private final String message;

    @Builder
    public ErrorDto(ErrorCode res){
        this.code = res.getCode();
        this.message = res.getMessage();
    }
}
