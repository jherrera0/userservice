package com.backendchallenge.userservice.application.http.handler;

import com.backendchallenge.userservice.application.http.dto.AuthResponse;
import com.backendchallenge.userservice.application.http.mapper.IAuthResponseMapper;
import com.backendchallenge.userservice.domain.api.IAuthServicePort;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class AuthHandlerTest {

    private IAuthServicePort authServicePort;
    private IAuthResponseMapper authResponseMapper;
    private AuthHandler authHandler;

    @BeforeEach
    void setUp() {
        authServicePort = Mockito.mock(IAuthServicePort.class);
        authResponseMapper = Mockito.mock(IAuthResponseMapper.class);
        authHandler = new AuthHandler(authServicePort, authResponseMapper);
    }

    @Test
    void login_withValidCredentials_returnsAuthResponse() {
        String email = ConstTest.EMAIL_VALID;
        String password = ConstTest.PASSWORD_VALID;
        AuthResponse expectedResponse = new AuthResponse(ConstTest.VALID_TOKEN);

        when(authServicePort.login(email, password)).thenReturn(ConstTest.VALID_TOKEN);
        when(authResponseMapper.toAuthResponse(ConstTest.VALID_TOKEN)).thenReturn(expectedResponse);

        AuthResponse result = authHandler.login(email, password);

        assertEquals(expectedResponse, result);
    }

    @Test
    void login_withInvalidCredentials_returnsNull() {
        String email = ConstTest.INVALID_EMAIL_TEST;
        String password = ConstTest.PASSWORD_VALID;

        when(authServicePort.login(email, password)).thenReturn(null);
        when(authResponseMapper.toAuthResponse(null)).thenReturn(null);

        AuthResponse result = authHandler.login(email, password);

        assertNull(result);
    }
}