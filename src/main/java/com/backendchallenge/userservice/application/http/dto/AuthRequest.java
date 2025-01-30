package com.backendchallenge.userservice.application.http.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;

    public AuthRequest(String userEmail, String userPassword) {
        this.username = userEmail;
        this.password = userPassword;
    }
}