package com.backendchallenge.userservice.application.http.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequest {
    @NotNull
    @Email
    private String username;
    @NotNull
    @NotBlank
    private String password;

    public AuthRequest(String userEmail, String userPassword) {
        this.username = userEmail;
        this.password = userPassword;
    }
}