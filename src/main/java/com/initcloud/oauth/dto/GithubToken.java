package com.initcloud.oauth.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GithubToken extends Token{

    private Long expiresIn;
    private Long refreshTokenExpiresIn;
    private String scope;
    private String tokenType;

    @Builder
    public GithubToken(String accessToken, String refreshToken, Long expiresIn, Long refreshTokenExpiresIn, String scope, String tokenType) {
        super(accessToken, refreshToken);
        this.expiresIn = expiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        this.scope = scope;
        this.tokenType = tokenType;
    }

    public static GithubToken toDto(GithubToken token){
        return GithubToken.builder()
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .expiresIn(token.getExpiresIn())
                    .refreshTokenExpiresIn(token.getRefreshTokenExpiresIn())
                    .scope(token.getScope())
                    .tokenType(token.getTokenType())
                .build();
    }
}
