package com.backendchallenge.userservice.infrastructure.configuration.feign;

import com.backendchallenge.userservice.domain.until.JwtConst;
import com.backendchallenge.userservice.domain.until.TokenHolder;
import com.backendchallenge.userservice.infrastructure.configuration.exceptionhandler.FeignExceptionHandler;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientsConfig {
    @Bean
    public static RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = TokenHolder.getToken();
            if (token != null && !token.isEmpty()) {
                requestTemplate.header(JwtConst.HEADER_STRING, token);
            }
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignExceptionHandler();
    }
}
