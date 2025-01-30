package com.backendchallenge.userservice.domain.usecase;

import com.backendchallenge.userservice.domain.exception.authexceptions.BadCredentialException;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.spi.IAuthPersistencePort;
import com.backendchallenge.userservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AuthCaseTest {

    private final IAuthPersistencePort authPersistencePort = Mockito.mock(IAuthPersistencePort.class);
    private final AuthCase authCase = new AuthCase(authPersistencePort);

    @Test
    void login_withValidCredentials_returnsToken() {
        String email = ConstTest.EMAIL_VALID;
        String password = ConstTest.PASSWORD_VALID;
        String token = ConstTest.VALID_TOKEN;
        User user = new User();

        when(authPersistencePort.validateCredentials(email, password)).thenReturn(true);
        when(authPersistencePort.authenticate(email, password)).thenReturn(user);
        when(authPersistencePort.generateToken(user)).thenReturn(token);

        String result = authCase.login(email, password);

        assertEquals(token, result);
    }

    @Test
    void login_withInvalidCredentials_throwsBadCredentialException() {
        String email = ConstTest.INVALID_EMAIL_TEST;
        String password = ConstTest.PASSWORD_VALID;

        when(authPersistencePort.validateCredentials(email, password)).thenReturn(false);

        assertThrows(BadCredentialException.class, () -> authCase.login(email, password));
    }
}