package com.backendchallenge.userservice.application.http.handler;

import com.backendchallenge.userservice.application.http.handler.interfaces.IAuthHandler;
import com.backendchallenge.userservice.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthHandler implements IAuthHandler {
    private final IAuthServicePort authServicePort;
    @Override
    public String login(String email, String password) {
        return authServicePort.login(email, password);
    }
}
