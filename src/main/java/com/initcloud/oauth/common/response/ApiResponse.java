package com.initcloud.oauth.common.response;

import com.initcloud.oauth.common.exception.ApiException;
import com.initcloud.oauth.common.response.error.ErrorCode;
import com.initcloud.oauth.common.response.error.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;


@AllArgsConstructor
@Builder
@Getter
public class ApiResponse<T> {

    private char success;
    private T data;
    private ErrorDto error;

    public ApiResponse(@Nullable T data) {
        this.success = 'y';
        this.data = data;
        this.error = null;
    }

    public static ResponseEntity<ApiResponse> throwException(ApiException e){
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ApiResponse.builder()
                            .success('n')
                            .data(null)
                            .error(new ErrorDto(e.getErrorCode()))
                        .build());
    }

    public static ResponseEntity<ApiResponse> throwException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                            .success('n')
                            .data(null)
                            .error(new ErrorDto(ErrorCode.ERROR_5001))
                        .build());
    }
}
