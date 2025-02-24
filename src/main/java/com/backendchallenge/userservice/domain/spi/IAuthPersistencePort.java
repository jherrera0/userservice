package com.backendchallenge.userservice.domain.spi;

import com.backendchallenge.userservice.domain.model.User;

public interface IAuthPersistencePort {
    User authenticate(String email, String password);
    String generateToken(User user);
    boolean validateCredentials(String userEmail, String userPassword);

}
