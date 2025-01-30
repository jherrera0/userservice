package com.backendchallenge.userservice.application.http.handler;

import com.backendchallenge.userservice.application.http.dto.AuthResponse;
import com.backendchallenge.userservice.application.http.handler.interfaces.IAuthHandler;
import com.backendchallenge.userservice.application.http.mapper.IAuthResponseMapper;
import com.backendchallenge.userservice.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthHandler implements IAuthHandler {
    private final IAuthServicePort authServicePort;
    private final IAuthResponseMapper authResponseMapper;
    @Override
    public AuthResponse login(String email, String password) {
        return authResponseMapper.toAuthResponse(authServicePort.login(email, password));
    }
}
