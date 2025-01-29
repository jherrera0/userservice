package com.backendchallenge.userservice.domain.spi;

import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.model.User;

public interface IUserPersistencePort {
    void saveUserWithRole(User user, Role role);
}
