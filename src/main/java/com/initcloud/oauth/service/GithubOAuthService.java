package com.initcloud.oauth.service;


import com.initcloud.oauth.common.config.UrlEnv;
import com.initcloud.oauth.common.util.HttpParam;
import com.initcloud.oauth.common.util.HttpRequest;
import com.initcloud.oauth.common.util.Random;
import com.initcloud.oauth.security.config.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GithubOAuthService implements OAuthService{

    private final Properties properties;

    @Override
    public void getAuthorize(){
        HttpRequest request = new HttpRequest();
        HttpParam.Query query = new HttpParam.Query();

        query.add("client_id", properties.getAppClientId());
        query.add("redirect_uri", properties.getCallback());
        query.add("state", Random.getUUID());

        request.get(null, UrlEnv.URL_GITHUB_ID_GET.getUrl(), null, query.getQuery());
    }

    @Override
    public String callback(String code){
        return (String)requestGithubAccessToken(code);
    }

    private Object requestGithubAccessToken(String code){
        HttpRequest request = new HttpRequest();
        HttpParam.Query query = new HttpParam.Query();

        query.add("client_id", properties.getAppClientId());
        query.add("client_secret", properties.getAppClientSecret());
        query.add("redirect_uri", properties.getCallback());
        query.add("code", code);

        return request.post(null, null, UrlEnv.URL_REDIRECT_POST.toString(), null,  query.getQuery());
    }
}
