package com.initcloud.oauth.service;


import com.initcloud.oauth.common.config.UrlEnv;
import com.initcloud.oauth.common.util.HttpParam;
import com.initcloud.oauth.common.util.HttpRequest;
import com.initcloud.oauth.common.util.Random;
import com.initcloud.oauth.security.config.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GithubOAuthService implements OAuthService{

    private final Properties properties;

    @Value("${oauth.baseurl}")
    private String BASE_URL;

    @Value("${oauth.callback}")
    private String CALLBACK;

    @Value("${spring.security.oauth2.client.registration.github.clientId}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.github.clientSecret}")
    private String CLIENT_SECRET;

    @Override
    public void getAuthorize(){
        HttpRequest request = new HttpRequest();
        HttpParam.Query query = new HttpParam.Query();

        query.add("client_id", properties.getAppClientId());
        query.add("redirect_uri", properties.getBaseUrl() + UrlEnv.GITHUB_CALLBACK);
        query.add("state", Random.getUUID());

        request.get(null, properties.getBaseUrl(),null, query.getQuery());
    }

    @Override
    public String callback(String code){
        Object token = requestGithubAccessToken(code);

        return (String)token;
    }

    private Object requestGithubAccessToken(String code){
        HttpRequest request = new HttpRequest();
        HttpParam.Query query = new HttpParam.Query();

        query.add("client_id", properties.getAppClientId());
        query.add("client_secret", properties.getAppClientSecret());
        query.add("redirect_uri", CALLBACK);
        query.add("code", code);

        return request.post(null, null, UrlEnv.URL_REDIRECT_POST.toString(), null,  query.getQuery());
    }
}
