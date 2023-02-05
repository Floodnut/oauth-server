package com.initcloud.oauth.service;

public interface OAuthService {

    void getAuthorize();
    Object callback(String code);
}
