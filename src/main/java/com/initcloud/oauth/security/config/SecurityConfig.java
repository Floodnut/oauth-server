package com.initcloud.oauth.security.config;


import com.initcloud.oauth.security.service.OAuthUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    OAuthUserDetailService oAuthUserDetailsService() {
        return new OAuthUserDetailService();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
    throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http)
    throws Exception {
        http.cors()
            .and()
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**", "/oauth2/**").permitAll()
                .anyRequest().authenticated();
    }
}
