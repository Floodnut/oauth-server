package com.initcloud.oauth.controller;



import com.initcloud.oauth.common.response.ApiResponse;
import com.initcloud.oauth.dto.GithubToken;
import com.initcloud.oauth.service.GithubOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/oauth/github")
@RequiredArgsConstructor
public class GithubOAuthController {

    private final GithubOAuthService oAuthService;
;
    @GetMapping("/auth")
    public void authorizeGithub(){
        oAuthService.getAuthorize();
    }

    @GetMapping("/callback")
    public ResponseEntity<ApiResponse<String>> callback(
            @RequestParam String code,
            @RequestParam String state){

        String response = oAuthService.callback(code);

        return ResponseEntity.ok()
                .body(new ApiResponse<>(response));
    }

    @GetMapping("/github")
    public ResponseEntity<ApiResponse<GithubToken>> getToken(
            @RequestParam(value = "access_token") String accessToken,
            @RequestParam(value = "refresh_token") String refreshToken,
            @RequestParam(value = "expires_in") Long expiresIn,
            @RequestParam(value = "refresh_token_expires_in") Long refreshTokenExpiresIn,
            @RequestParam(value = "scope") String scope,
            @RequestParam(value = "token_type") String tokenType){

        return ResponseEntity.ok()
                .body(new ApiResponse<>(new GithubToken(accessToken, refreshToken,
                        expiresIn, refreshTokenExpiresIn, scope, tokenType)));
    }
}
