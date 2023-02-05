package com.initcloud.oauth.service;


import com.initcloud.oauth.common.config.UrlEnv;
import com.initcloud.oauth.common.util.HttpParam;
import com.initcloud.oauth.common.util.HttpRequest;
import com.initcloud.oauth.security.config.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class GithubOAuthService implements OAuthService{

    private final Properties properties;

    @Value("")
    private String BASE_URL;

    @Value("")
    private String CALLBACK;

    @Value("")
    private String CLIENT_ID;

    @Value("")
    private String CLIENT_SECRET;

    @Override
    public void getAuthorize(){
        HttpRequest request = new HttpRequest();
        HttpParam.Query query = new HttpParam.Query();

        query.add("client_id", properties.getAppClientId());
        query.add("redirect_uri", properties.getBaseUrl() + UrlEnv.GITHUB_CALLBACK);
        query.add("state", "TEMP_RANDOM_STRING_FOR_STATE_adf23gwesdgq560wfwkqe9lfnwqfwqnlgommwf23f2");

        request.get(properties.getBaseUrl(),null, query.getQuery());
    }

    @Override
    public String callback(String code){
        return "aa";
    }
}
