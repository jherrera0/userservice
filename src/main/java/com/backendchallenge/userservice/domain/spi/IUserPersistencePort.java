package com.backendchallenge.userservice.domain.spi;

import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.model.User;

public interface IUserPersistencePort {
    void saveUserWithRole(User user, Role role);
    Boolean existsUserWithRole(Long userId, Role role);
    Long findUserIdByEmail(String email);
    boolean existsUserIdByEmail(String email);

    boolean existsUserId(Long userId);
    String getPhone(Long userId);
}
