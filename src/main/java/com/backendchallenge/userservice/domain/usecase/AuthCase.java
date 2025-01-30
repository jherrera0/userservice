package com.backendchallenge.userservice.domain.usecase;

import com.backendchallenge.userservice.domain.api.IAuthServicePort;
import com.backendchallenge.userservice.domain.exception.authexceptions.BadCredentialException;
import com.backendchallenge.userservice.domain.spi.IAuthPersistencePort;

public class AuthCase implements IAuthServicePort {
    private final IAuthPersistencePort authPersistencePort;

    public AuthCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String password) {
        if (authPersistencePort.validateCredentials(email, password)) {
            return authPersistencePort.generateToken(authPersistencePort.authenticate(email, password));
        } else {
            throw new BadCredentialException();
        }
    }
}
