package com.initcloud.oauth.service;

import javax.servlet.http.HttpServletResponse;

public interface OAuthService {

    void getAuthorize(HttpServletResponse response);
    Object callback(String code, String state);
}
