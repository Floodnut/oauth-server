package com.initcloud.oauth.service;


import com.initcloud.oauth.common.config.UrlEnv;
import com.initcloud.oauth.common.exception.ApiException;
import com.initcloud.oauth.common.response.error.ErrorCode;
import com.initcloud.oauth.common.util.HttpParam;
import com.initcloud.oauth.common.util.HttpRequest;
import com.initcloud.oauth.common.util.Random;
import com.initcloud.oauth.security.config.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Service
public class GithubOAuthService implements OAuthService{

    private final Properties properties;

    @Override
    public void getAuthorize(HttpServletResponse response) {
        HttpRequest request = new HttpRequest();
        HttpParam.Query query = new HttpParam.Query();

        query.add("client_id", properties.getAppClientId());
        query.add("redirect_uri", properties.getCallback());
        query.add("state", Random.getUUID());

        String redirect = request.redirect(response, null, UrlEnv.URL_GITHUB_ID_GET.getUrl(), null, query.getQuery());

        try{
            response.sendRedirect(redirect);
        }catch (IOException e){
            throw new ApiException(ErrorCode.ERROR_5001);
        }
    }

    @Override
    public String callback(String code, String state){
        return (String)requestGithubAccessToken(code, state);
    }

    private Object requestGithubAccessToken(String code, String state) {
        try {
            HttpRequest request = new HttpRequest();
            HttpParam.Query query = new HttpParam.Query();

            query.add("client_id", properties.getAppClientId());
            query.add("client_secret", properties.getAppClientSecret());
            query.add("redirect_uri", properties.getCallback());
            query.add("code", code);
            query.add("state", state);

            return request.post(null, null, UrlEnv.URL_REDIRECT_POST.getUrl(), null, query.getQuery());

        } catch (Exception e) {
            throw new ApiException(ErrorCode.ERROR_5001);
        }
    }
}
