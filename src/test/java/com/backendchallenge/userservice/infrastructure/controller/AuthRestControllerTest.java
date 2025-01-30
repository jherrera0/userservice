package com.backendchallenge.userservice.infrastructure.controller;

import com.backendchallenge.userservice.application.http.dto.AuthRequest;
import com.backendchallenge.userservice.application.http.dto.AuthResponse;
import com.backendchallenge.userservice.application.http.handler.interfaces.IAuthHandler;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthRestControllerTest {

    @InjectMocks
    private AuthRestController authRestController;

    @Mock
    private IAuthHandler authHandler;

    @Test
    void login_withValidCredentials_returnsAuthResponse() {
        AuthRequest authRequest = new AuthRequest(ConstTest.EMAIL_VALID, ConstTest.PASSWORD_VALID);
        AuthResponse authResponse = new AuthResponse(ConstTest.VALID_TOKEN);

        when(authHandler.login(authRequest.getUsername(), authRequest.getPassword())).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authRestController.login(authRequest);

        assertEquals(ResponseEntity.ok(authResponse), response);
    }
}