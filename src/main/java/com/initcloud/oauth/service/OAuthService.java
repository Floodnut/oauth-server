package com.initcloud.oauth.service;

import javax.servlet.http.HttpServletResponse;

public interface OAuthService {

    void getAuthorize(HttpServletResponse response, String redirectUrl);
    Object callback(String code, String state);
}
