package com.initcloud.oauth.dto;


import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    private String accessToken;
    private String refreshToken;
}
