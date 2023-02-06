package com.initcloud.oauth.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UrlEnv {
    URL_GITHUB_ID_GET("https://github.com/login/oauth/authorize"),
    URL_REDIRECT_POST("https://github.com/login/oauth/access_token"),
    URL_API_ACCESS_GET("https://api.github.com/user");

    private String url;
}
