package com.backendchallenge.userservice.application.http.handler.interfaces;

import com.backendchallenge.userservice.application.http.dto.AuthResponse;

public interface IAuthHandler {
    AuthResponse login(String email, String password);
}