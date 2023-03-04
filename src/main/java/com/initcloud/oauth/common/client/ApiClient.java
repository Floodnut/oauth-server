package com.initcloud.oauth.common.client;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import com.initcloud.oauth.dto.Token;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiClient {
	public static WebClient getClient(String base) {
		return WebClient.builder().baseUrl(base).defaultHeader(HttpHeaders.CONTENT_TYPE).build();
	}

	public static Mono<String> getTokens(String baseUrl, Token.Request body) {
		return ApiClient.getClient(baseUrl)
			.post()
			.uri("/login/oauth/access_token")
			.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
			.body(body, Token.Request.class)
			.retrieve()
			.bodyToMono(String.class);
	}

	public static Mono<String> postTokensToOrigin(String baseUrl, String tokens) {
		return ApiClient.getClient(baseUrl)
			.post()
			.uri("/api/gitAuth")
			.header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
			.body(Mono.just(tokens), String.class)
			.retrieve()
			.bodyToMono(String.class);
	}
}
