package com.initcloud.oauth.security.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@RequiredArgsConstructor
@Slf4j
public class Properties {

    private final Environment environment;

    @Getter
    private String baseUrl;

    @Getter
    private String appClientId;

    @Getter
    private String appClientSecret;

    @PostConstruct
    public void oAuthInit(){
        this.baseUrl = environment.getProperty("BASE_URL");
        this.appClientId = environment.getProperty("GITHUB_APP_CLIENT_ID");
        this.appClientSecret = environment.getProperty("GITHUB_APP_CLIENT_SECRET");

        log.info("GITHUB_APP_CLIENT_ID is " + this.appClientId);
        log.info("GITHUB_APP_CLIENT_SECRET is " + this.appClientSecret);
    }
}
